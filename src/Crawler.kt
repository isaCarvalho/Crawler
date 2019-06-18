import java.io.IOException
import kotlin.collections.ArrayList
import java.net.URL
import java.sql.SQLException
import java.sql.Statement

class Crawler
{
    companion object
    {
        // Metodo que retornara uma lista com as urls referenciadas por uma determinada URL passada por parametro
        fun getLinks(url : String) : List<String>?
        {
            // variavel que contem a expressao regular de uma url
            val regex = Regex("http(s)?://(\\w+\\.)*(\\w+)(:\\d+)?")

            // Lista com os links refenciados pela pagina
            val links : ArrayList<String>? = ArrayList()

            try
            {
                // conteudo da pagina buscada
                val conteudo : String = URL(url).readText()

                // matches guarda todas as urls que correspondentes a expressao regular acima
                val matches: Sequence<MatchResult> = regex.findAll(conteudo, 0)

                // para cada elemento correspondente a uma url, este elemento e adicionado na lista de links
                matches.forEach { match -> links!!.add(match.value) }
            }
            catch (ex: IOException)
            {
                println("Erro ao processar URL: " + ex)
            }

            return links
        }

        // Metodo que armazena a lista de links no banco de dados
        fun storeLinks(links : List<String>)
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
                Query.query("INSERT INTO links (url, ranking) VALUES ('${link}', ${ranking})")

                ranking = 0
            }
        }

        // Metodo que busca se uma url contem uma palavra-chave -- ordenado pelo ranking de cada url
        fun search(word : String)
        {
            val results = Query.select("SELECT url FROM links " +
                    "WHERE url LIKE '%${word}%'" +
                    "ORDER BY ranking DESC")

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

