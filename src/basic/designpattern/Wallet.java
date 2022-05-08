package basic.designpattern;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

/**
 * 封装的前提就是：权限的控制 java 中 private public 等特殊的修饰符
 * 对外暴露属性的读写功能时按照方法进行封装，不可以让用户看到或可以操作形如 wallet.id=3 这种操作
 * 用户仅需要知道增加和减少balance的操作方法
 * 可以读取到各个属性的值
 *
 * @author yudazhi
 */
public class Wallet {
    private String id;
    private long createTime;
    private BigDecimal balance;
    private long balanceLastModifiedTime;

    public Wallet() {
        //  id generator
//        this.id = IdGenerator.getInstance().generator();
        this.createTime = System.currentTimeMillis();
        this.balance = BigDecimal.ZERO;
        this.balanceLastModifiedTime = System.currentTimeMillis();
    }

    // get all

    public String getId() {
        return id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public long getBalanceLastModifiedTime() {
        return balanceLastModifiedTime;
    }

    // set only balance and time

    public void increaseBalance(BigDecimal increaseAmount) {
        if (increaseAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidParameterException("InvalidAmountException");
        }
        this.balance.add(increaseAmount);
        this.balanceLastModifiedTime = System.currentTimeMillis();
    }

    public void decreaseBalance(BigDecimal decreaseAmount) {
        if (decreaseAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidParameterException("InvalidAmountException");
        }
        if (decreaseAmount.compareTo(this.balance) > 0) {
            throw new InvalidParameterException("InsufficientAmountException");
        }
        this.balance.subtract(decreaseAmount);
        this.balanceLastModifiedTime = System.currentTimeMillis();
    }
}
