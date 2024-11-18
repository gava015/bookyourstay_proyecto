package co.edu.uniquindio.bookyourstay.util;

import java.util.Random;

public class RandomUtil {

    public static int generarCodigoAleatorio() {
        Random random = new Random();
        return  100000 + random.nextInt(900000);
    }
}
