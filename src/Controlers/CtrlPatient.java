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

public class CtrlPatient {
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlPatient() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<String> getAllPatients() {
        ArrayList<String> lesPatients = new ArrayList<>();
        try
        {
            ps = cnx.prepareStatement("SELECT nomPatient FROM Patient ");
            rs = ps.executeQuery();
            while (rs.next()) {
                String patient = new String(rs.getString("nomPatient"));
                lesPatients.add(patient);
            }
            ps.close();
            rs.close();
        }catch (SQLException ex) {
            Logger.getLogger(CtrlPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesPatients;
        }


    public int getIdPatientByName(String nomPat)
    {
        int numPat = 0;

        try {
            ps = cnx.prepareStatement("select numPat from patient inner join consultation on numPatient= idPatient and nomPatient = ?");
            ps.setString(1, nomPat);
            rs = ps.executeQuery();
            rs.next();
            numPat = rs.getInt("numPat");
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numPat;


    }
}
