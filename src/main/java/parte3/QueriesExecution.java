package parte3;

import java.util.List;

public class QueriesExecution {

    public static void main(String[] args) {
        AlunoDao alunoDao = new AlunoDao();

        List<Aluno> alunos = AlunoDao.list();
        alunos.stream().forEach(System.out::println);
    }
}
