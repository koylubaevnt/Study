package com.koylubaevnt.stepik.java.basecourse;

public final class ComplexNumber {
    private final double re;
    private final double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

	@Override
	public int hashCode() {
		final int hashCode = 31;
		int result = 1;
		
		result = result * hashCode + Double.hashCode(re);
		result = result * hashCode + Double.hashCode(im);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		ComplexNumber other = (ComplexNumber) obj;
		if(Double.compare(this.im, other.im)
				+ Double.compare(this.re, other.re) != 0) {
			return false;
		} 
		return true;
	}
    
    
}