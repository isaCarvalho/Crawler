fun main()
{
    while (true)
    {
        println("Escolha a opcao desejada:\n")
        println("1 - Buscar URL")
        println("2 - Buscar palavra-chave")
        println("3 - Sair")

        when (readLine()!!.toInt())
        {
            1 -> {
                println("Digite a URL desejada: ")
                val url = readLine()!!.toString()

                val links = Crawler.getLinks(url)!!

                Crawler.printLinks(links)
            }
            2 -> {
                println("Digite a palavra-chave a ser buscada")
                val palavra = readLine()!!.toString()

                Crawler.search(palavra)
            }
            3 -> return
        }
    }
}
