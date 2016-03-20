package auladao;

import auladao.dao.PessoaDAO;
import auladao.model.Pessoa;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Main {

    
    public static void main(String[] args) {
        
        PessoaDAO dao = new PessoaDAO();
        LocalDate date = LocalDate.of(1988, Month.AUGUST, 31);
        
        Pessoa p1 = new Pessoa("juacy willian", "juacy@willian.com", "12345678901", "5521123456789", "m", date);
        Pessoa p2 = new Pessoa("juacy willian", "juacy@willian.co4m", "12345678902", "5521123456789", "m", date);
        Pessoa p3 = new Pessoa("juacy willian", "juacy@willian.c3om", "12345678903", "5521123456789", "m", date);
        Pessoa p4 = new Pessoa("juacy willian", "juacy@willian.2com", "12345678904", "5521123456789", "m", date);
        Pessoa p5 = new Pessoa("juacy willian", "juacy@willian1.com", "12345678905", "5521123456789", "m", date);
        
        int id1 = dao.insert(p1);
        int id2 = dao.insert(p2);
        int id3 = dao.insert(p3);
        int id4 = dao.insert(p4);
        int id5 = dao.insert(p5);
        
        List<Pessoa> pessoas = dao.findAll();
        for (Pessoa pessoinha : pessoas) {
            System.out.println(pessoinha);
        }
        
        int delete = dao.deleteMany(pessoas);
        System.out.println(delete);
        
    }
}
