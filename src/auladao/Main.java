package auladao;

import auladao.dao.PessoaDAO;
import auladao.model.Pessoa;
import java.time.LocalDate;
import java.time.Month;

public class Main {

    
    public static void main(String[] args) {
        
        PessoaDAO dao = new PessoaDAO();
        LocalDate date = LocalDate.of(1988, Month.AUGUST, 31);
        Pessoa p = new Pessoa("juacy willian", "juacy@willian.com", "12345678901", "5521123456789", "m", date);
        int id = dao.insert(p);
        System.out.println(id);
        
        
    }
}
