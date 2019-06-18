import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class Query
{
    companion object
    {
        // executa uma query passada por parametro
        fun query(query : String)
        {
            val conn = Conexao.openConnection()

            try
            {
                val stmt : Statement = conn!!.createStatement()

                stmt.executeUpdate(query)
            }
            catch (ex: SQLException)
            {
                ex.printStackTrace()
            }

            Conexao.closeConnection()
        }

        // executa um select e retorna seu resultado
        fun select(query: String) : ResultSet?
        {
            val conn = Conexao.openConnection()

            try
            {
                val stmt = conn!!.createStatement()

                return stmt!!.executeQuery(query)
            }
            catch (ex : SQLException)
            {
                ex.printStackTrace()
            }

            return null
        }
    }
}