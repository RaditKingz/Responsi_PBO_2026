package Responsi;

public class Main {
    public static void main(String[] args) {

        System.out.println("##### [1] STATE & BEHAVIOR #####");
        PompaPertalite p1 = new PompaPertalite("PT-01");
        p1.tampilkanInfo();
        double total = p1.hitungTotal(20);
        System.out.println("Total 20 L Pertalite: Rp" + (long) total);

        System.out.println("\n##### [2] IMMUTABILITY #####");
        System.out.println("Konstanta NAMA_SPBU : " + Pompa.NAMA_SPBU);
        System.out.println("Konstanta STOK_MIN  : " + Pompa.STOK_MINIMAL);
        System.out.println("Kode pompa (final)  : " + p1.getKodePompa() + " (tidak punya setter)");

        System.out.println("\n##### [3] ENKAPSULASI & OVERLOADING #####");
        PompaPertalite p2 = new PompaPertalite("PT-02", 300, 40);
        p2.setBatasLiterPerTransaksi(50);
        System.out.println("Stok default PT-01 : " + p1.getStokLiter() + " L");
        System.out.println("Stok custom  PT-02 : " + p2.getStokLiter() + " L, batas "
                + p2.getBatasLiterPerTransaksi() + " L");

        System.out.println("\n##### [4] INHERITANCE #####");
        PompaPertamax px = new PompaPertamax("PX-01", 500, 5);
        PompaSolar ps = new PompaSolar("SL-01");
        Pompa[] semuaPompa = { p1, p2, px, ps };
        for (Pompa p : semuaPompa) {
            System.out.println(p.getKodePompa() + " -> " + p.getClass().getSimpleName()
                    + " (instanceof Pompa: " + (p instanceof Pompa) + ")");
        }

        System.out.println("\n##### [5] SUPER CHAINING #####");
        System.out.println("(super(...) di constructor & super.tampilkanInfo() di method)");
        px.tampilkanInfo();
        ps.tampilkanInfo();

        System.out.println("\n##### [6] METHOD OVERRIDING #####");
        System.out.println("hitungTotal(10) Pertalite: Rp" + (long) p1.hitungTotal(10));
        System.out.println("hitungTotal(10) Pertamax (diskon 5%): Rp" + (long) px.hitungTotal(10));

        System.out.println("\n##### [7] ABSTRACT CLASS #####");
        System.out.println("Pompa tidak bisa di-new; layaniPengisian() wajib di-implement subclass.");

        System.out.println("\n##### [8] LOGIKA STATE (IF/ELSE) #####");
        p2.layaniPengisian(60);    // ditolak: melebihi batas 50 L
        p2.layaniPengisian(50);    // berhasil
        p2.layaniPengisian(50);    // berhasil
        p2.layaniPengisian(50);    // berhasil
        p2.layaniPengisian(50);    // berhasil -> stok 100 -> pompa terkunci
        p2.layaniPengisian(10);    // ditolak: pompa terkunci
        px.layaniPengisian(450);   // stok jadi 50 -> terkunci
        px.layaniPengisian(10);    // ditolak
        ps.layaniPengisian(100);   // berhasil
        p2.isiUlangStok(500);      // isi ulang -> aktif lagi
        p2.layaniPengisian(10);    // berhasil lagi
        p2.tampilkanInfo();

        System.out.println("\n##### [9] POLIMORFISME INTERFACE #####");
        Pembayaran bayar1 = new PompaPertalite("PT-03");
        Pembayaran bayar2 = new PompaPertamax("PX-02", 800, 10);
        Pembayaran bayar3 = new PompaSolar("SL-02");
        Pembayaran[] daftarBayar = { bayar1, bayar2, bayar3 };
        for (Pembayaran b : daftarBayar) {
            System.out.println(b.getClass().getSimpleName() + " 30 L = Rp" + (long) b.hitungTotal(30));
        }

        System.out.println("\n##### [10] MULTIPLE INTERFACE #####");
        System.out.println("PompaPertamax & PompaSolar implements Pembayaran, Laporan");
        Laporan lap1 = px;
        Laporan lap2 = ps;
        lap1.cetakLaporan();
        lap2.cetakLaporan();
        Pembayaran bayarPx = px;   // objek yang sama, dilihat sebagai Pembayaran
        System.out.println("px sebagai Pembayaran, 5 L = Rp" + (long) bayarPx.hitungTotal(5));
    }
}
