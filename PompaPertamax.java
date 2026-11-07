package Responsi;

public class PompaPertamax extends Pompa implements Pembayaran, Laporan {

    private double diskonPersen;

    public PompaPertamax(String kodePompa) {
        super(kodePompa, "Pertamax", 12200);
        this.diskonPersen = 0;
    }

    public PompaPertamax(String kodePompa, double stokAwal, double diskonPersen) {
        super(kodePompa, "Pertamax", 12200, stokAwal);
        this.diskonPersen = diskonPersen;
    }

    @Override
    public boolean layaniPengisian(double liter) {
        System.out.println(">> " + getKodePompa() + " melayani " + liter + " L Pertamax");
        if (!isAktif()) {
            System.out.println("   Ditolak: pompa sedang terkunci.");
            return false;
        } else if (liter > getStokLiter()) {
            double sisa = getStokLiter();
            System.out.println("   Stok kurang, hanya dilayani " + sisa + " L.");
            kurangiStok(sisa);
            cekStokMinimal();
            return true;
        } else {
            kurangiStok(liter);
            cekStokMinimal();
            System.out.println("   Berhasil. Sisa stok: " + getStokLiter() + " L");
            return true;
        }
    }

    @Override
    public double hitungTotal(double liter) {
        double kotor = getHargaPerLiter() * liter;
        return kotor - (kotor * diskonPersen / 100);
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
        System.out.println("   Diskon member: " + diskonPersen + "%");
    }

    public double getDiskonPersen() { return diskonPersen; }
    public void setDiskonPersen(double diskonPersen) { this.diskonPersen = diskonPersen; }
}
