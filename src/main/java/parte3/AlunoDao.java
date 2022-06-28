package parte3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class AlunoDao {

    // CONSULTAR

    public static List<Aluno> list(){
        // Vai preparar a lista que vai retornar alunos depois de ser consultado no BD

        List<Aluno>alunos = new ArrayList<>();

        try(Connection conn =  ConnectionFactory.getConnection() ){

         PreparedStatement prst = conn.prepareStatement("SELECT * FROM  aluno");
        ResultSet rs = prst.executeQuery();

       while(rs.next()){
           Aluno aluno = new Aluno(
                   rs.getInt("id"),
                   rs.getString("nome"),
                   rs.getInt("idade"),
                   rs.getString("estado")
           );


           alunos.add(aluno);
       }

        }catch (Exception e){

        }

        //ConnectionFactory.getConnection();

        return alunos;
    }
}
