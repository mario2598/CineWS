/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mario
 */
@Entity
@Table(name = "REVIEW")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r")
    , @NamedQuery(name = "Review.findByRevId", query = "SELECT r FROM Review r WHERE r.revId = :revId")
    , @NamedQuery(name = "Review.findByRevCalif", query = "SELECT r FROM Review r WHERE r.revCalif = :revCalif")
    , @NamedQuery(name = "Review.findByRevOpinion", query = "SELECT r FROM Review r WHERE r.revOpinion = :revOpinion")})
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "REV_ID")
    private Long revId;
    @Column(name = "REV_CALIF")
    private Long revCalif;
   // @Size(max = 150)
    @Column(name = "REV_OPINION")
    private String revOpinion;
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Movie movieId;
    @JoinColumn(name = "USU_ID", referencedColumnName = "USU_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuId;

    public Review() {
    }

    public Review(Long revId) {
        this.revId = revId;
    }

    public Long getRevId() {
        return revId;
    }

    public void setRevId(Long revId) {
        this.revId = revId;
    }

    public Long getRevCalif() {
        return revCalif;
    }

    public void setRevCalif(Long revCalif) {
        this.revCalif = revCalif;
    }

    public String getRevOpinion() {
        return revOpinion;
    }

    public void setRevOpinion(String revOpinion) {
        this.revOpinion = revOpinion;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public void setMovieId(Movie movieId) {
        this.movieId = movieId;
    }

    public Usuario getUsuId() {
        return usuId;
    }

    public void setUsuId(Usuario usuId) {
        this.usuId = usuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (revId != null ? revId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.revId == null && other.revId != null) || (this.revId != null && !this.revId.equals(other.revId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Review[ revId=" + revId + " ]";
    }
    
}
