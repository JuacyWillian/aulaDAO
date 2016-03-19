package auladao.dao;

import auladao.connection.FactoryConnection;
import auladao.model.Pessoa;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaDAO implements DaoJdbc<Pessoa> {

    private final FactoryConnection fabrica;

    private final String SELECT = "SELECT * FROM pessoa";
    private final String SELECT_ID = "SELECT * FROM pessoa WHERE id=?";
    private final String SELECT_CPF = "SELECT * FROM pessoa WHERE cpf=?";
    private final String SELECT_NOME = "SELECT * FROM pessoa WHERE nome=?";

    private final String INSERT = "INSERT INTO pessoa(nome,email,cpf,telefone,sexo,dataNascimento) VALUES(?,?,?,?,?,?)";
    private final String DELETE = "DELETE from pessoa where id=?";
    private final String UPDATE = "UPDATE pessoa set nome=?, email=?, cpf=?, telefone=?, sexo=?, dataNascimento=? where id=?";

    public PessoaDAO() {
        fabrica = new FactoryConnection();
    }

    /**
     *
     * Adiciona um objeto do tipo Pessoa ao Banco de Dados
     *
     * @param p Pessoa a ser adicionada ao Banco de dados
     * @return se adicionado com sucesso retorna o id do objeto adicionado ao
     * banco de dados, caso contrario retorna 0.
     */
    @Override
    public int insert(Pessoa p) {
        try (
                Connection con = fabrica.create();
                PreparedStatement ps = con.prepareStatement(INSERT)) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            ps.setString(3, p.getCpf());
            ps.setString(4, p.getTelefone());
            ps.setString(5, p.getSexo());
            ps.setDate(6, Date.valueOf(p.getDataDeNascimento()));

            if (ps.executeUpdate() > 0) {
                Pessoa pessoaBanco = findByCPF(p.getCpf());
                return pessoaBanco.getId();
            }

        } catch (SQLException ex) {
            System.out.println("deu merda");
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    /**
     * Atualiza um objeto do banco de dados
     *
     * @param p objeto do tipo Pessoa a ser atualizado
     * @return retorna true caso o objeto tenha sido atualizado com sucesso, e
     * false caso não seja possivel atualizá-lo.
     */
    @Override
    public boolean update(Pessoa p) {
        boolean status = false;

        try (
                Connection con = fabrica.create();
                PreparedStatement ps = con.prepareStatement(UPDATE);) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            ps.setString(3, p.getCpf());
            ps.setString(4, p.getTelefone());
            ps.setString(5, p.getSexo());
            ps.setDate(6, Date.valueOf(p.getDataDeNascimento()));
            ps.setLong(7, p.getId());
            if (ps.executeUpdate() > 0) {
                status = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    /**
     * Atualiza vários objetos do banco de dados
     *
     * @param listPessoas Lista com os objetos a serem atualizados.
     * @return O numero de objetos atualizados.
     */
    @Override
    public int updateMany(List<Pessoa> listPessoas) {
        int result = 0;
        for (Pessoa p : listPessoas) {
            if (update(p)) {
                result++;
            }
        }
        return result;
    }

    @Override
    public boolean delete(Pessoa p) {
        boolean status = false;
        try (
                Connection con = fabrica.create();
                PreparedStatement ps = con.prepareStatement(DELETE)) {
            ps.setInt(1, p.getId());
            if (ps.executeUpdate() > 0) {
                status = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    @Override
    public int deleteMany(List<Pessoa> listPessoas) {
        int result = 0;
        for (Pessoa p : listPessoas) {
            if (delete(p)) {
                result++;
            }
        }
        return result;
    }

    @Override
    public Pessoa findById(int id) {
        Pessoa p = new Pessoa();
        try (
                Connection con = fabrica.create();
                PreparedStatement ps = con.prepareStatement(SELECT_ID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setEmail(rs.getString("email"));
                p.setCpf(rs.getString("cpf"));
                p.setTelefone(rs.getString("telefone"));
                p.setSexo(rs.getString("sexo"));
                p.setDataDeNascimento(rs.getDate("dataNascimento").toLocalDate());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public Pessoa findByCPF(String cpf) {
        Pessoa p = new Pessoa();
        try (
                Connection con = fabrica.create();
                PreparedStatement ps = con.prepareStatement(SELECT_CPF)) {

            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setEmail(rs.getString("email"));
                p.setCpf(rs.getString("cpf"));
                p.setTelefone(rs.getString("telefone"));
                p.setSexo(rs.getString("sexo"));
                p.setDataDeNascimento(rs.getDate("dataNascimento").toLocalDate());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public Pessoa FindByName(String nome) {
        Pessoa p = new Pessoa();
        try (
                Connection con = fabrica.create();
                PreparedStatement ps = con.prepareStatement(SELECT_NOME)) {

            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setEmail(rs.getString("email"));
                p.setCpf(rs.getString("cpf"));
                p.setTelefone(rs.getString("telefone"));
                p.setSexo(rs.getString("sexo"));
                p.setDataDeNascimento(rs.getDate("dataNascimento").toLocalDate());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public List<Pessoa> findAll() {
        List<Pessoa> lista = new ArrayList();

        try (
                Connection con = fabrica.create();
                PreparedStatement ps = con.prepareStatement(SELECT);) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setEmail(rs.getString("email"));
                p.setCpf(rs.getString("cpf"));
                p.setTelefone(rs.getString("telefone"));
                p.setSexo(rs.getString("sexo"));
                p.setDataDeNascimento(rs.getDate("dataNascimento").toLocalDate());
                lista.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

}
