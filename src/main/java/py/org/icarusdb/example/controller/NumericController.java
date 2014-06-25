package py.org.icarusdb.example.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class NumericController
{
    private BigDecimal bdecimal = null;
    
    @PostConstruct
    private void init()
    {
        System.out.println("init " + this.getClass().getSimpleName());
        bdecimal = BigDecimal.TEN;
        System.out.println("..");
    }
    
    public BigDecimal getPrintNumeric()
    {
        MathContext mc = new MathContext(15, RoundingMode.HALF_EVEN);
        BigDecimal numeric = new BigDecimal(0.000000000000001, mc);
        System.out.println("numeric is : " + numeric);
        System.out.println("..");
        BigDecimal rounded0 = numeric.setScale(0, RoundingMode.HALF_EVEN);
        System.out.println("numeric with HALF_EVEN RoundingMode and scale 0: " + rounded0);
        BigDecimal rounded15 = numeric.setScale(15, RoundingMode.HALF_EVEN);
        System.out.println("numeric with HALF_EVEN RoundingMode and scale 15: " + rounded15);
        System.out.println("..");
        BigDecimal sum = bdecimal.add(numeric);
        System.out.println("sum is: " + sum);
        System.out.println("sum with HALF_EVEN RoundingMode and scale 15 is: " + sum.setScale(15, RoundingMode.HALF_EVEN));
        return sum;
    }
    
    

}
