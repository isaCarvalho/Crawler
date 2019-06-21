import java.io.IOException
import kotlin.collections.ArrayList
import java.net.URL
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt

class Crawler
{
    companion object
    {
        // LEMBRAR DE TESTAR ISSO AQUI TBM

        // Metodo que retornara uma lista com as urls referenciadas por uma determinada URL passada por parametro
        fun getLinks(url : String) : List<String>?
        {
            // Lista com os links refenciados pela pagina
            val links : ArrayList<String>? = ArrayList()

            // busca se a URL passada por parametro já existe na base de dados
            val busca = searchURL(url)

            // Caso não esteja, ela será inserida na base de dados, juntamente com seus links
            if (busca == null)
            {
                // insere a nova url no banco e retorna seu valor
                Query.query("INSERT INTO urls (url) VALUES ('${url}');")
                val urlInserida = searchURL(url)

                // variavel que contem a expressao regular de uma url
                val regex = Regex("http(s)?://(\\w+\\.)*(\\w+)(:\\d+)?")

                try
                {
                    // conteudo da pagina buscada
                    val conteudo : String = URL(url).readText()

                    // matches guarda todas as urls que correspondentes a expressao regular acima
                    val matches: Sequence<MatchResult> = regex.findAll(conteudo, 0)

                    // para cada elemento correspondente a uma url, este elemento e adicionado na lista de links
                    matches.forEach { match -> links!!.add(match.value) }

                    // guarda os links de cada url buscada de acordo com seu id
                    storeLinks(links!!, urlInserida!!.getString("id").toInt())
                }
                catch (ex: IOException)
                {
                    println("Erro ao processar URL: " + ex)
                }
            }
            // caso a url já esteja cadastrada na base de dados, seus links sao buscados e adicionado na lista de links
            else
            {
                val results = searchLinkByUrl(busca.getString("id").toInt())

                while (results!!.next())
                    links!!.add(results.getString("url"))
            }

            // retorna os links referenciados pela url passada por parametro
            return links
        }

        // Metodo que armazena a lista de links no banco de dados
        fun storeLinks(links : List<String>, id_url : Int)
        {
            // setUrls guarda todos os links distintos buscados
            val setUrls = links.distinct()

            // ranking armazenara a quantidade de referencias aquela url
            var ranking = 0

            // para cada url distinta, e calculada a sua posicao no ranking
            setUrls.forEach{ link ->

                for (url in links)
                {
                    if (url == link)
                        ranking++
                }

                // a url e sua posicao no ranking sao armazenados no banco
                Query.query("INSERT INTO links (url, ranking, id_url) VALUES ('${link}', ${ranking}, ${id_url});")

                ranking = 0
            }
        }

        // Metodo que procura uma url na tabela urls
        fun searchURL(url: String) : ResultSet?
        {
            val results = Query.select("SELECT id, url FROM urls WHERE url = '${url}';")

            if (results!!.next())
                return results

            return null
        }

        // Metodo que procura um link pelo id da url
        fun searchLinkByUrl(id_url: Int) : ResultSet?
        {
            val results = Query.select("SELECT url FROM links WHERE id_url = ${id_url};")

            if (results!!.next())
                return results

            return null
        }

        // Metodo que busca se uma url contem uma palavra-chave -- ordenado pelo ranking de cada url
        fun search(word : String)
        {
            val results = Query.select("SELECT url FROM links " +
                    "WHERE url LIKE '%${word}%'" +
                    "ORDER BY ranking DESC;")

            while (results!!.next())
                println("URL: ${results.getString("url")}")
        }

        // Metodo que imprime todas as urls distintas buscadas
        fun printLinks(links : List<String>)
        {
            val setUrls = links.distinct()

            setUrls.forEach { link -> println(link) }
        }
    }
}

