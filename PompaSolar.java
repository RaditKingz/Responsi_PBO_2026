public class PompaSolar extends Pompa implements Pembayaran, Laporan {

    private int kadarSulfurPpm;

    public PompaSolar(String kodePompa) {
        super(kodePompa, "Biosolar", 6800);
        this.kadarSulfurPpm = 2000;
    }

    public PompaSolar(String kodePompa, double stokAwal, int kadarSulfurPpm) {
        super(kodePompa, "Biosolar", 6800, stokAwal);
        this.kadarSulfurPpm = kadarSulfurPpm;
    }

    @Override
    public boolean layaniPengisian(double liter) {
        System.out.println(">> " + getKodePompa() + " melayani " + liter + " L Biosolar");
        if (!isAktif()) {
            System.out.println("   Ditolak: pompa sedang terkunci.");
            return false;
        } else if (liter > getStokLiter()) {
            System.out.println("   Ditolak: stok tidak cukup.");
            return false;
        } else {
            kurangiStok(liter);
            cekStokMinimal();
            System.out.println("   Berhasil. Sisa stok: " + getStokLiter() + " L");
            return true;
        }
    }

    @Override
    public double hitungTotal(double liter) {
        return getHargaPerLiter() * liter;
    }

    @Override
    public void cetakLaporan() {
        System.out.println("=== LAPORAN " + getKodePompa() + " (" + getNamaBahanBakar() + ") ===");
        System.out.println("Terjual   : " + getTotalTerjualLiter() + " L");
        System.out.println("Pendapatan: " + rupiah(hitungPendapatan()));
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("   Kadar sulfur: " + kadarSulfurPpm + " ppm");
    }

    public int getKadarSulfurPpm() { return kadarSulfurPpm; }
    public void setKadarSulfurPpm(int kadarSulfurPpm) { this.kadarSulfurPpm = kadarSulfurPpm; }
}
