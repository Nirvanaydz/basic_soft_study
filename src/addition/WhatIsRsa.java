package addition;

public class WhatIsRsa {
    //        public static void main(String[] args) {
//            //设置两个互质数的初始值，为了简化运算，设置为17和19
//            long p = 17;
//            long q = 19;
//
//            //计算公钥对（E，N）
//            long N = p * q;
//            long L = ArithmeticUtils.lcm(p - 1, q - 1);
//            long E = 0;
//            for (int i = 2; i < L; i++) {
//                if (ArithmeticUtils.gcd(i, L) == 1) {
//                    E = i;
//                    break;
//                }
//            }
//            System.out.printf("公钥=（E, N） = （%d, %d)%n", E, N);
//
//            //计算私钥对（D，N）
//            long D = 0;
//            for (int i = 2; i < L; i++) {
//                if (E * i % L == 1) {
//                    D = i;
//                    break;
//                }
//            }
//            System.out.printf("私钥=（D, N） = （%d, %d)%n", D, N);
//
//            //使用秘钥对完成加解密，随机生成一个原文数字
//            long src = (long) (new Random().nextDouble() * (N - 1));
//
//            //加密后的密文
//            long cipher = ArithmeticUtils.pow(src, (int) E) % N;
//
//            //反向解密
//            BigInteger pow = ArithmeticUtils.pow(BigInteger.valueOf(cipher), BigInteger.valueOf(D));
//            int src2 = pow.mod(BigInteger.valueOf(N)).intValue();
//
//            System.out.printf("加密：原文: %d, 密文= (原文的E次方) mod N = (%d ^ %d) mod %d=%d%n",
//                    src, src, E, N, cipher);
//
//            System.out.printf("解密：密文: %d, 原文= (密文的D次方) mod N = (%d ^ %d) mod %d=%d%n",
//                    cipher, cipher, D, N, src2);
//        }
}
