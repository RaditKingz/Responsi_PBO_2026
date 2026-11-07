public class PompaPertalite extends Pompa implements Pembayaran {

    private double batasLiterPerTransaksi;

    public PompaPertalite(String kodePompa) {
        super(kodePompa, "Pertalite", 10000);
        this.batasLiterPerTransaksi = 60;
    }

    public PompaPertalite(String kodePompa, double stokAwal, double batasLiterPerTransaksi) {
        super(kodePompa, "Pertalite", 10000, stokAwal);
        this.batasLiterPerTransaksi = batasLiterPerTransaksi;
    }

    @Override
    public boolean layaniPengisian(double liter) {
        System.out.println(">> " + getKodePompa() + " melayani " + liter + " L Pertalite");
        if (!isAktif()) {
            System.out.println("   Ditolak: pompa sedang terkunci.");
            return false;
        } else if (liter > batasLiterPerTransaksi) {
            System.out.println("   Ditolak: melebihi batas " + batasLiterPerTransaksi + " L per transaksi.");
            return false;
        } else if (liter > getStokLiter()) {
            System.out.println("   Ditolak: stok tidak cukup.");
            return false;
        } else {
            kurangiStok(liter);   // state berubah
            cekStokMinimal();     // bisa mengunci pompa
            System.out.println("   Berhasil. Sisa stok: " + getStokLiter() + " L");
            return true;
        }
    }

    @Override
    public double hitungTotal(double liter) {
        return getHargaPerLiter() * liter;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("   Batas per transaksi: " + batasLiterPerTransaksi + " L");
    }

    public double getBatasLiterPerTransaksi() { return batasLiterPerTransaksi; }
    public void setBatasLiterPerTransaksi(double batasLiterPerTransaksi) {
        this.batasLiterPerTransaksi = batasLiterPerTransaksi;
    }
}
