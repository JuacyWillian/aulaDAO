/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auladao.dao;

import auladao.model.Pessoa;
import java.time.LocalDate;
import java.time.Month;
import junit.framework.TestCase;
import org.junit.Test;

public class PessoaDAOTest extends TestCase{
    private final PessoaDAO dao;
    private final Pessoa p;
    
    
    public PessoaDAOTest() {
        dao = new PessoaDAO();
        LocalDate date = LocalDate.of(1988, Month.AUGUST, 31);
        p = new Pessoa("juacy willian", "juacy@willian.com", "12345678901", "5521993471707", "m", date);
    }

    @Test
    public void testInsert() {
        int result = dao.insert(p);
        assertEquals(1, result);
    }
    
}
