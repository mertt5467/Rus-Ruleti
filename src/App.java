import java.util.Scanner;
import java.util.Random;

public class App {
    static Scanner input = new Scanner(System.in);
    static Random r = new Random();

    static int[] gun = new int[6]; // Weapon and number of bullets

    static int record = 0; // The number of times the user beat the computer in a row

    static boolean cGame = true;

    public static void main(String[] args) throws Exception {
        System.out.println("---------- RUS RULETI ----------");
        System.out.println("Baslamak icin enter'i tuslayiniz.");
        input.nextLine();
        Thread.sleep(1000);
        gameMenu();
    }

    static void gameMenu() throws Exception {
        while (true) {
            System.out.println("---------- OYUN MODLARI ----------");
            System.out.println("1 - Bilgisayara Karsi Oyna (Rekor: " + record + ")");
            System.out.println("2 - Versus Modu");
            System.out.println("3 - Cikis");
            System.out.print("Secim: ");
            int choose = input.nextInt();
            input.nextLine();
            switch (choose) {
                case 1 -> {
                    computerGame();
                    cGame = true;
                }
                case 2 -> {
                    versusGame();
                    cGame = false;
                }
                case 3 -> System.exit(0);
                default -> System.out.println("Yanlis tuslama.");
            }
        }
    }

    static void computerGame() throws Exception {
        System.out.println("Oyun baslatiliyor...");
        Thread.sleep(2000);
        boolean start = rollDice(0);
        System.out.println("Tur Basliyor...");
        Thread.sleep(2000);
        bulletChoose(start);
        int step = 1;
        int control = 0;
        for (int i = (start) ? 0 : 1;; i++) {
            if (i % 2 == 0) {
                control = playerTour(step);
            } else {
                control = computerTour(step);
            }
            if (control == 1) {
                System.out.println("Ana menuye donuluyor...");
                Thread.sleep(1500);
                return;
            }
            step++;
        }
    }

    static void versusGame() throws Exception {
        System.out.println("Oyun baslatiliyor...");
        Thread.sleep(2000);
        boolean start = rollDice(1);
        System.out.println("Tur Basliyor...");
        Thread.sleep(2000);
        if (start) {
            System.out.println("---------- 1. OYUNCU ----------");
        } else {
            System.out.println("---------- 2. OYUNCU ----------");
        }
        bulletChoose(true);
        int step = 1;
        int control = 0;
        int player;
        for (int i = (start) ? 0 : 1;; i++) {
            if (i % 2 == 0) {
                System.out.println("---------- 1. OYUNCU ----------");
                control = playerTour(step);
                player = 2;
            } else {
                System.out.println("---------- 2. OYUNCU ----------");
                control = playerTour(step);
                player = 1;
            }
            if (control == 1) {
                System.out.println(player + ". Oyuncu kazandi.");
                Thread.sleep(2000);
                System.out.println("Ana menuye donuluyor...");
                Thread.sleep(1500);
                return;
            }
            step++;
        }
    }

    static boolean rollDice(int x) throws Exception {
        System.out.println("Zar Atma Asamasi");
        Thread.sleep(1000);
        boolean start;
        if (x == 0) {
            start = computerDice(); // true = Player start //false = Computer start
        } else {
            start = versusDice(); // true = 1. Player start //false = 2. Player start
        }
        return start;
    }

    static boolean computerDice() throws Exception {
        while (true) {
            System.out.println("Zarinizi atmak icin enter'a basin.");
            input.nextLine();
            int pDice = r.nextInt(6) + 1;
            Thread.sleep(500);
            System.out.println("Zariniz: " + pDice);
            Thread.sleep(1000);
            System.out.println("Bilgisayar zar atiyor...");
            Thread.sleep(1500);
            int cDice = r.nextInt(6) + 1;
            System.out.println("Bilgisayarin zari: " + cDice);
            Thread.sleep(1000);
            if (pDice > cDice) {
                System.out.println("Zar atisini kazandiniz, baslamak istediginiz sirayi seciniz.");
                System.out.println("1 - Ilk tur");
                System.out.println("2 - Ikinci tur");
                while (true) {
                    System.out.print("Secim: ");
                    int chooseTourP = input.nextInt();
                    input.nextLine();
                    switch (chooseTourP) {
                        case 1 -> {
                            return true;
                        }
                        case 2 -> {
                            return false;
                        }
                        default -> System.out.println("Yanlis tuslama.");
                    }
                    Thread.sleep(500);
                }
            } else if (cDice > pDice) {
                System.out.println("Bilgisayar zar atisini kazandi.");
                Thread.sleep(1000);
                System.out.println("Bilgisayar karar veriyor...");
                Thread.sleep(2000);
                boolean chooseTourC = r.nextBoolean();
                if (chooseTourC == false) {
                    System.out.println("Bilgisayar ilk baslamayi secti.");
                } else {
                    System.out.println("Bilgisayar ikinci baslamayi secti.");
                }
                Thread.sleep(1000);
                return chooseTourC;
            } else {
                System.out.println("Berabere. Tekrar atiliyor.");
                Thread.sleep(1500);
            }
        }
    }

    static boolean versusDice() throws Exception {
        boolean diceWinner;
        while (true) {
            System.out.println("---------- 1. OYUNCU ----------");
            System.out.println("Zar atmak icin enter'i tuslayiniz.");
            input.nextLine();
            int dice1 = r.nextInt(6) + 1;
            Thread.sleep(1500);
            System.out.println("1. Oyuncunun zari: " + dice1);
            Thread.sleep(1000);
            System.out.println("---------- 2. OYUNCU ----------");
            System.out.println("Zar atmak icin enter'i tuslayiniz.");
            input.nextLine();
            int dice2 = r.nextInt(6) + 1;
            Thread.sleep(1500);
            System.out.println("2. Oyuncunun zari: " + dice2);
            Thread.sleep(1000);
            if (dice1 > dice2) {
                System.out.println("---------- 1. OYUNCU ----------");
                diceWinner = true;
            } else if (dice2 > dice1) {
                System.out.println("---------- 2. OYUNCU ----------");
                diceWinner = false;
            } else {
                System.out.println("Berabere. Tekrar atiliyor.");
                Thread.sleep(1500);
                continue;
            }
            System.out.println("Zar atisini kazandiniz, baslamak istediginiz sirayi seciniz.");
            System.out.println("1 - Ilk tur");
            System.out.println("2 - Ikinci tur");
            boolean result = true;
            boolean choose = false;
            while (choose == false) {
                System.out.print("Secim: ");
                int chooseTourP = input.nextInt();
                input.nextLine();
                switch (chooseTourP) {
                    case 1 -> {
                        result = true;
                        choose = true;
                    }
                    case 2 -> {
                        result = false;
                        choose = true;
                    }
                    default -> System.out.println("Yanlis tuslama.");
                }
                Thread.sleep(500);
            }
            if (diceWinner == false) {
                return !result;
            }
            return result;
        }
    }

    static void bulletChoose(boolean start) throws Exception {
        System.out.println("Silah Doldurma Asamasi");
        Thread.sleep(1000);
        int bulletNumber;
        if (start) {
            System.out.println("Koymak istediginiz mermi miktarini seciniz. (Min 1, Max 5)");
            while (true) {
                System.out.print("Mermi Sayisi: ");
                bulletNumber = input.nextInt();
                input.nextLine();
                if (bulletNumber > 0 && bulletNumber < 6) {
                    bulletPlacement(bulletNumber);
                    break;
                } else {
                    System.out.println("Yanlis tuslama.");
                    Thread.sleep(500);
                }
            }
        } else {
            System.out.println("Bilgisayar mermi sayisini seciyor...");
            bulletNumber = computerBulletChoose();
            Thread.sleep(2000);
            System.out.println("Bilgisayar mermi sayisini secti.");
            Thread.sleep(1000);
            bulletPlacement(bulletNumber);
        }
    }

    static int computerBulletChoose() {
        int possibility = r.nextInt(15);
        if (possibility < 5) {
            return 1;
        } else if (possibility < 9) {
            return 2;
        } else if (possibility < 12) {
            return 3;
        } else if (possibility < 14) {
            return 4;
        } else {
            return 5;
        }
    }

    static void bulletPlacement(int x) throws Exception {
        System.out.println("Mermi dolduruluyor...");
        Thread.sleep(2000);
        int bullet;
        boolean correct = false;
        while (correct == false) {
            gun[0] = 0;
            gun[1] = 0;
            gun[2] = 0;
            gun[3] = 0;
            gun[4] = 0;
            gun[5] = 0;
            for (int i = 0; i < x; i++) {
                bullet = r.nextInt(6);
                gun[bullet] += 1;
            }
            for (int control : gun) {
                if (control > 1) {
                    correct = false;
                    break;
                } else {
                    correct = true;
                }
            }
        }
        System.out.println("Mermiler Yerlestirildi.");
        Thread.sleep(1000);
    }

    static int playerTour(int step) throws Exception {
        System.out.println(step + ". SARJOR");
        System.out.println("SIKMAK ICIN ENTER'I TUSLAYIN");
        input.nextLine();
        if (step != 6) {
            Thread.sleep(2500);
        }
        Thread.sleep(1000);
        if (gun[step - 1] == 0) {
            System.out.println("BOS");
            Thread.sleep(1000);
            return 0;
        } else {
            System.out.println("BOM - SILAH PATLADI.");
            if (cGame == true) {
                record = 0;
            }
            Thread.sleep(1000);
            return 1;
        }
    }

    static int computerTour(int step) throws Exception {
        System.out.println(step + ". SARJOR");
        System.out.println("BILGISAYAR SIKIYOR...");
        if (step != 6) {
            Thread.sleep(2500);
        }
        Thread.sleep(1000);
        if (gun[step - 1] == 0) {
            System.out.println("BOS");
            Thread.sleep(1000);
            return 0;
        } else {
            System.out.println("BOM - SILAH PATLADI.");
            Thread.sleep(500);
            System.out.println("Kazandiniz.");
            record++;
            Thread.sleep(1000);
            return 1;
        }
    }
}
