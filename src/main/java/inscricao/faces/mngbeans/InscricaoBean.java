package inscricao.faces.mngbeans;

import inscricao.persistence.entity.Candidato;
import inscricao.persistence.entity.Idioma;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import utfpr.faces.support.PageBean;
import utfpr.persistence.controller.IdiomaJpaController;
import utfpr.persistence.controller.JpaController;

/**
 *
 * @author Wilson
 */
@ManagedBean
@SessionScoped
public class InscricaoBean extends PageBean {
//    private static final Idioma[] idiomas = {
//        new Idioma(1, "Inglês"),
//        new Idioma(2, "Alemão"),
//        new Idioma(3, "Francês")
//    };
    private Candidato candidato = new Candidato(new Idioma(1)); // inicialmente ingles
    //private List<SelectItem> idiomaItemList;
    
    private boolean linkGRUVisivel = false;
    private SimpleDateFormat formatDataVencto = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatCompetencia = new SimpleDateFormat("MM/yyyy");
    private boolean informativo;
    private boolean correio;
    private boolean email;
    private boolean cpfInvalido = false;
    private boolean editarInscricao = false;

    public boolean isEditarInscricao() {
        return editarInscricao;
    }

    public boolean isCpfInvalido() {
        return cpfInvalido;
    }
    
    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    private boolean validaCandidato() {
        JpaController ctl = new JpaController();
        EntityManager em = ctl.getEntityManager();
        try {
            Candidato c = em.find(Candidato.class, candidato.getCpf());
            return c == null;
        } finally {
            em.close();
        }
    }
    
    public List<Idioma> getIdiomas() {
//        List<Idioma> idiomas;
//        try {
//            IdiomaJpaController ctl = new IdiomaJpaController();
//            idiomas = ctl.findAll();
//        } catch (Exception e) {
//            idiomas = new ArrayList<>(0);
//            log("Lista de idiomas", e);
//        }
        ArrayList<Idioma> idiomas = new ArrayList<>();
        
        idiomas.add(new Idioma(1, "Inglês"));
        idiomas.add(new Idioma(2, "Alemão"));
        idiomas.add(new Idioma(3, "Francês"));
        
        return new ArrayList<> (idiomas);
        
//        return idiomas;
    }

    public boolean isInformativo() {
        return informativo;
    }

    public void setInformativo(boolean informativo) {
        this.informativo = informativo;
    }

    public boolean isCorreio() {
        return correio;
    }

    public void setCorreio(boolean correio) {
        this.correio = correio;
    }

    public boolean isEmail() {
        return email;
    }

    public void setEmail(boolean email) {
        this.email = email;
    }
    
    public boolean isLinkGRUVisivel() {
        return linkGRUVisivel;
    }
    
    public String getDataVencimento() {
        GregorianCalendar hoje = new GregorianCalendar();
        hoje.add(Calendar.DAY_OF_MONTH, 1);
        return formatDataVencto.format(hoje.getTime());
    }
    
    public String getCompetencia() {
        GregorianCalendar hoje = new GregorianCalendar();
        hoje.add(Calendar.DAY_OF_MONTH, 1);
        return formatCompetencia.format(hoje.getTime());
    }
    
    public void informativoValueChangeListener(ValueChangeEvent event) {
        correio = false;
        email = false;
    }
    
    public String editarInscricao() {        
        editarInscricao = true;

        return "inscricao";
    }
    
    public String novaInscricao() {
        editarInscricao = false;

        candidato = new Candidato(new Idioma(1)); // inicialmente ingles
        
        return "inscricao";
    }
    
    public String inscricaoAction() {
        
//        JpaController ctl = new JpaController();
//        EntityManager em = ctl.getEntityManager();
//        try {
//            if (validaCandidato()) {
//                em.getTransaction().begin();
//                em.persist(candidato);
//                em.getTransaction().commit();
//                
//                info("Inscrição realizada com sucesso");
//                linkGRUVisivel = true;
//                
//                return "confirma";
//            } else {
//                error("Este CPF já está inscrito");
//                linkGRUVisivel = true;
//                return "inicial";
//            }
//        } catch (Exception e) {
//            log("Incrição", e);
//            error("Não foi possível completar a operação");
//        } finally {
//            em.close();
//        }
//        
//        return null;        
        
        candidato.setDataHora(new Date());
        candidato.setIdioma(candidato.getIdioma());
        
        InscricoesBean inscricoesBean = (InscricoesBean)getBean("inscricoesBean");
        
        if (editarInscricao) {
            if (inscricoesBean.atualizarInscricao(candidato))
                return "confirma";
            else
                return null;
            
        } else {
            if (inscricoesBean.adicionarInscricao(candidato))
            return "confirma";
        else 
            cpfInvalido = true;
            return "inscricao";
        }
    }
}
