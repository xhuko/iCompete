/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icompete.entity;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Xhulio
 */
@Entity
public class Rule {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 2048)
    private String text;

    public Long getId() {
        return id;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.getText());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Rule)) {
            return false;
        }
        final Rule other = (Rule) obj;
        if (!Objects.equals(this.getText(), other.getText())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
