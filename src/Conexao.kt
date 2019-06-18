import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.*

class Conexao
{
    companion object
    {
        private var connectionProps = Properties()
        var conn : Connection? = null

        fun openConnection() : Connection?
        {
            connectionProps.put("user", "root")
            connectionProps.put("password", "")

            try
            {
                Class.forName("com.mysql.jdbc.Driver")

                conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/crawler", connectionProps)

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