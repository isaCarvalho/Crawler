import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.*

/** Classe de conexao com a base de dados
 * IMPORTANTE: caso seja preferivel usar o SGBD MySQL, use os blocos de comandos nos comentarios
 */

class Conexao
{
    companion object
    {
        private var connectionProps = Properties()
        var conn : Connection? = null

        fun openConnection() : Connection?
        {
            connectionProps.put("user", "postgres")
            connectionProps.put("password", "123456")
            connectionProps.put("useSSL", "false")

            /** Para a conexao com o MySQL use
             *
             * connectionProps.put("user", "root")
             * connectionProps.put("password", "")
             * connectionProps.put("useSSL", "false")
             */

            try
            {
                Class.forName("org.postgresql.Driver")
                /** Class.forName("com.mysql.jdbc.Driver") */

                conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/crawler", connectionProps)
                /** conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/crawler", connectionProps) */

                return conn
            }
            catch (ex : SQLException)
            {
                ex.printStackTrace()
                return null
            }
        }

        fun closeConnection()
        {
            conn!!.close()
        }
    }
}