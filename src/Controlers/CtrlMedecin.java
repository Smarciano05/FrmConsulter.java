package Controlers;

import Entities.Medicament;
import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlMedecin
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlMedecin() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<String> getAllMedecins()
    {
        ArrayList<String> lesMedecins = new ArrayList<>();
            try {
                ps = cnx.prepareStatement("SELECT nomMedecin FROM medecin inner join consulation on numMedecin = idMedecin");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String medecin = new String(rs.getString("nomMedecin"));
                    lesMedecins.add(medecin);
                }
                ps.close();
                rs.close();
            }catch (SQLException ex) {
                Logger.getLogger(CtrlMedecin.class.getName()).log(Level.SEVERE, null, ex);
            }
            return lesMedecins;
        }



    public int getIdMedecinByName(String nomMed)
    {
        int numMed = 0;

        // A vous de jouer

        return numMed;
    }
}
