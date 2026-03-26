package com.osmosyscol.datasuite.near.utils;

/**
 * Implementacion monadica simple de either
 * la parte A: left conserva el error
 * la parte B: right conserva el payload de interes
 * 
 */
public class Either<A,B> {
    private A left = null;
    private B right = null;
 
    private Either(A a,B b) {
        left = a;
        right = b;
    }
 
    public static <A,B> Either<A,B> left(A a) {
        return new Either<A,B>(a,null);
    }
 
    public A left() {
        return left;
    }
 
    public boolean isLeft() {
        return left != null;
    }
 
    public boolean isRight() {
        return right != null;
    }
 
    public B right() {
        return right;
    }
 
    public static <A,B> Either<A,B> right(B b) {
        return new Either<A,B>(null,b);
    }
    
    
	public B getOrElse(B whenLeft ) {
		B _value ;
		if( this.isLeft() ) {
			_value = whenLeft;
		} else {
			_value = this.right();
		}
		return _value;
	}
    
 
   //public void fold(F<A> leftOption, F<B> rightOption) {
   //     if(right == null)
   //         leftOption.f(left);
   //     else
   //         rightOption.f(right);
   // }
}
