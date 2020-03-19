package com.mycompany.unicafe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author masse
 */
public class KassapaateTest {

    Kassapaate paate;
    Maksukortti kortti;

    @Before
    public void setUp() {
        paate = new Kassapaate();
    }

    @Test
    public void rahaJaLounaatOikeinAlussa() {
        assertEquals(paate.kassassaRahaa(), 100000);
        assertEquals(paate.maukkaitaLounaitaMyyty(), 0);
        assertEquals(paate.edullisiaLounaitaMyyty(), 0);
    }

    @Test
    public void edullinenOnnistuuJosRahaaTarpeeksi() {
        assertEquals(paate.syoEdullisesti(400), 160);
        assertEquals(paate.kassassaRahaa(), 100240);
        assertEquals(paate.edullisiaLounaitaMyyty(), 1);
    }

    @Test
    public void maukasOnnistuuJosRahaaTarpeeksi() {
        assertEquals(paate.syoMaukkaasti(400), 0);
        assertEquals(paate.kassassaRahaa(), 100400);
        assertEquals(paate.maukkaitaLounaitaMyyty(), 1);

    }

    @Test
    public void edullinenOstoEiOnnistuJosLiianVahanRahaa() {
        assertEquals(paate.syoEdullisesti(200), 200);
        assertEquals(paate.kassassaRahaa(), 100000);
        assertEquals(paate.edullisiaLounaitaMyyty(), 0);
    }

    @Test
    public void maukasOstoEiOnnistuJosLiianVahanRahaa() {
        assertEquals(paate.syoMaukkaasti(200), 200);
        assertEquals(paate.kassassaRahaa(), 100000);
        assertEquals(paate.maukkaitaLounaitaMyyty(), 0);
    }

    @Test
    public void edullinenKorttiOstoOnnistuuJosTarpeeksiRahaa() {
        kortti = new Maksukortti(400);
        assertTrue(paate.syoEdullisesti(kortti));
        assertEquals(paate.edullisiaLounaitaMyyty(), 1);
        assertEquals(paate.kassassaRahaa(), 100000);
    }

    @Test
    public void maukasKorttiOstoOnnistuuJosTarpeeksiRahaa() {
        kortti = new Maksukortti(400);
        assertTrue(paate.syoMaukkaasti(kortti));
        assertEquals(paate.maukkaitaLounaitaMyyty(), 1);
        assertEquals(paate.kassassaRahaa(), 100000);
    }

    @Test
    public void edullinenKorttiOstoEiOnnistuJosLiianVahanRahaa() {
        kortti = new Maksukortti(200);
        assertFalse(paate.syoEdullisesti(kortti));
        assertEquals(paate.edullisiaLounaitaMyyty(), 0);
        assertEquals(paate.kassassaRahaa(), 100000);
    }

    @Test
    public void maukasKorttiOstoEiOnnistuJosLiianVahanRahaa() {
        kortti = new Maksukortti(200);
        assertFalse(paate.syoMaukkaasti(kortti));
        assertEquals(paate.maukkaitaLounaitaMyyty(), 0);
        assertEquals(paate.kassassaRahaa(), 100000);
    }
    
    @Test
    public void rahanLatausToimiiOikein() {
        kortti = new Maksukortti(200);
        paate.lataaRahaaKortille(kortti, 300);
        assertEquals(kortti.saldo(), 500);
        assertEquals(paate.kassassaRahaa(), 100300);
    }
    
    @Test
    public void negatiivisenSummanLatausEiToimi() {
        kortti = new Maksukortti(100);
        paate.lataaRahaaKortille(kortti, -111);
        assertEquals(kortti.saldo(), 100);
        assertEquals(paate.kassassaRahaa(), 100000);
    }

}
