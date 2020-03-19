package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void kortinSaldoAsetetaanOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }

    @Test
    public void lataaminenLisaaRahaa() {
        kortti.lataaRahaa(100);
        assertEquals("saldo: 1.10", kortti.toString());
    }

    @Test
    public void saldoVaheneeJosRahaaOnTarpeeksi() {
        assertTrue(kortti.otaRahaa(10));
    }

    @Test
    public void saldoEiVaheneJosRahaaEiOleTarpeeksi() {
        assertFalse(kortti.otaRahaa(20));
    }
    
    @Test
    public void palauttaaOikeanSaldon() {
        assertEquals(kortti.saldo(), 10);
    }
}
