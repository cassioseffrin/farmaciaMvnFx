
package br.edu.cassio.farmaciamvnfx.database;

import java.sql.Connection;

public interface Database {
    
    public void desconectar(Connection conn);
    
}
