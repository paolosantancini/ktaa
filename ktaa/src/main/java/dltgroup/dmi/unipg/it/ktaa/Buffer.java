package dltgroup.dmi.unipg.it.ktaa;

import java.math.BigInteger;

/**
 * DltGroup Unipg Bistarelli Stefano, Mercanti Ivan, Santancini Paolo, Santini
 * Francesco
 */
public class Buffer {

    // unique istance
    private static Buffer instance = null;
    int x1, x2, e;
    BigInteger a, a0, b, rn, alpha, beta, A;
    BigInteger[] idlist = {BigInteger.ZERO, BigInteger.ZERO};
    String rgm;
    int lambda_group_max;

    public Buffer() {
    }

    // create object if it doesn't exist
    public static synchronized Buffer getIstance() {
        if (instance == null) {
            instance = new Buffer();
        }
        return instance;
    }

    public void setX1(int value) {
        x1 = value;
    }

    public void setX2(int value) {
        x2 = value;
    }

    public void setA(BigInteger value) {
        a = value;
    }

    public void setA0(BigInteger value) {
        a0 = value;
    }

    public void setB(BigInteger value) {
        b = value;
    }

    // se rigid number
    public void setRn(BigInteger value) {
        rn = value;
    }

    public void setRgm(String value) {
        rgm = value;
    }

    public void setMaxLambda(int value) {
        lambda_group_max = value;
    }

    public void setAlpha(BigInteger value) {
        alpha = value;
    }

    public void setBeta(BigInteger value) {
        beta = value;
    }

    public void setE(int value) {
        e = value;
    }

    public void setA_(BigInteger value) {
        A = value;
    }

    public void updateIDLIST(BigInteger i, BigInteger beta) {
        idlist[0] = i;
        idlist[1] = beta;
    }

    public int getX1() {
        return (x1);
    }

    public int getX2() {
        return (x2);
    }

    public int getMaxLambda() {
        return (lambda_group_max);
    }

    public BigInteger getRigidNumber() {
        return (rn);
    }

    public String getRgm() {
        return (rgm);
    }

    public BigInteger getA() {
        return (a);
    }

    public BigInteger getA0() {
        return (a0);
    }

    public BigInteger getB() {
        return (b);
    }

    public BigInteger getAlpha() {
        return (alpha);
    }

    public BigInteger getBeta() {
        return (beta);
    }

    public int getE() {
        return (e);
    }

    public BigInteger getA_() {
        return (A);
    }

}
