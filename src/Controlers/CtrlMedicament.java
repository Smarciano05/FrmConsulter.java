package Controlers;

import Entities.Consultation;
import Entities.Medicament;
import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlMedicament
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlMedicament() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<Medicament> getAllMedicaments()
    {
        ArrayList<Medicament> lesMedicaments = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT idMedoc , nomMedoc , prixMedoc   FROM `medicament`");
            rs = ps.executeQuery();
            while (rs.next()) {
                Medicament medicament = new Medicament(rs.getInt("idmedoc"), rs.getString("nomMedoc"), rs.getInt("prixMedoc"));
                lesMedicaments.add(medicament);
            }
            ps.close();
            rs.close();
        }catch (SQLException ex)
        {
            Logger.getLogger(CtrlMedicament.class.getName()).log(Level.SEVERE,null,ex);
        }
        return lesMedicaments;

    }
}
