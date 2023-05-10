package Controlers;

import Entities.Consultation;
import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlConsultation {
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlConsultation() {
        cnx = ConnexionBDD.getCnx();
    }

    public int getLastNumberOfConsultation() {
        int maxNumero = 0;
        try {
            ps = cnx.prepareStatement("SELECT idConsul FROM consultation inner join prescrire  on idConsult = idMedoc");
            rs = ps.executeQuery();
            rs.next();
            maxNumero = rs.getInt("idConsult");
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlConsultation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return maxNumero;
    }

    public void InsertConsultation(int idConsult, String dateConsultation, int numPatient, int numMedecin) {
        try {
            ps = cnx.prepareStatement("insert into consultation values(?,?,?,?)");
            ps.setInt(1, idConsult);
            ps.setInt(2, numPatient);
            ps.setInt(3, numMedecin);
            ps.setString(4,dateConsultation);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlConsultation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
