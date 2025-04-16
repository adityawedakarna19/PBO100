import java.util.ArrayList;
import java.util.Scanner;

public class StokAlatDapur {

    // Superclass
    static class AlatDapur {
        private int id;
        private String nama;
        private int stok;

        public AlatDapur(int id, String nama, int stok) {
            this.id = id;
            this.nama = nama;
            this.stok = stok;
        }

        public int getId() {
            return id;
        }

        public String getNama() {
            return nama;
        }

        public int getStok() {
            return stok;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public void setStok(int stok) {
            this.stok = stok;
        }

        public String getDetailTambahan() {
            return "";
        }

        @Override
        public String toString() {
            return "ID: " + id + " | Nama Alat: " + nama + " | Stok: " + stok + getDetailTambahan();
        }
    }

    // Subclass 1 - AlatMasak
    static class AlatMasak extends AlatDapur {
        private String bahan;

        public AlatMasak(int id, String nama, int stok, String bahan) {
            super(id, nama, stok);
            this.bahan = bahan;
        }

        public String getBahan() {
            return bahan;
        }

        public void setBahan(String bahan) {
            this.bahan = bahan;
        }

        @Override
        public String getDetailTambahan() {
            return " | Bahan: " + bahan;
        }
    }

    // Subclass 2 - AlatMakan
    static class AlatMakan extends AlatDapur {
        private String jenis;

        public AlatMakan(int id, String nama, int stok, String jenis) {
            super(id, nama, stok);
            this.jenis = jenis;
        }

        public String getJenis() {
            return jenis;
        }

        public void setJenis(String jenis) {
            this.jenis = jenis;
        }

        @Override
        public String getDetailTambahan() {
            return " | Jenis: " + jenis;
        }
    }

    private static ArrayList<AlatDapur> stokList = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan;

        while (true) {
            System.out.println("\n=== Aplikasi Stok Alat Dapur (Dapur Cerdas) ===");
            System.out.println("1. Tambah Alat");
            System.out.println("2. Lihat Stok Alat");
            System.out.println("3. Ubah Data Alat");
            System.out.println("4. Hapus Alat");
            System.out.println("5. Keluar");
            System.out.print("Masukkan pilihan: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    createAlat(input);
                    break;
                case 2:
                    readAlat();
                    break;
                case 3:
                    updateAlat(input);
                    break;
                case 4:
                    deleteAlat(input);
                    break;
                case 5:
                    System.out.println("Keluar dari aplikasi.");
                    input.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid! Silakan coba lagi.");
            }
        }
    }

    // Fungsi untuk menambah alat
    public static void createAlat(Scanner input) {
        System.out.println("\n--- Tambah Alat Dapur ---");
        System.out.println("1. Alat Masak");
        System.out.println("2. Alat Makan");
        System.out.print("Pilih jenis alat: ");
        int jenis = input.nextInt();
        input.nextLine();

        System.out.print("Masukkan nama alat: ");
        String nama = input.nextLine();
        System.out.print("Masukkan jumlah stok: ");
        int stok = input.nextInt();
        input.nextLine();

        AlatDapur alatBaru = null;

        if (jenis == 1) {
            System.out.print("Masukkan bahan alat masak (misal: stainless): ");
            String bahan = input.nextLine();
            alatBaru = new AlatMasak(nextId, nama, stok, bahan);
        } else if (jenis == 2) {
            System.out.print("Masukkan jenis alat makan (misal: keramik, plastik): ");
            String tipe = input.nextLine();
            alatBaru = new AlatMakan(nextId, nama, stok, tipe);
        } else {
            System.out.println("Jenis tidak valid.");
            return;
        }

        stokList.add(alatBaru);
        nextId++;
        System.out.println("Alat berhasil ditambahkan!");
    }

    // Fungsi untuk melihat daftar stok alat
    public static void readAlat() {
        System.out.println("\n--- Daftar Stok Alat Dapur ---");
        if (stokList.isEmpty()) {
            System.out.println("Belum ada alat yang ditambahkan.");
        } else {
            for (AlatDapur alat : stokList) {
                System.out.println(alat);
            }
        }
    }

    // Fungsi untuk mengubah data alat
    public static void updateAlat(Scanner input) {
        System.out.println("\n--- Ubah Data Alat Dapur ---");
        System.out.print("Masukkan ID alat yang ingin diubah: ");
        int id = input.nextInt();
        input.nextLine();

        AlatDapur alatUpdate = null;
        for (AlatDapur alat : stokList) {
            if (alat.getId() == id) {
                alatUpdate = alat;
                break;
            }
        }

        if (alatUpdate == null) {
            System.out.println("Alat dengan ID tersebut tidak ditemukan.");
            return;
        }

        System.out.print("Masukkan nama baru: ");
        String namaBaru = input.nextLine();
        System.out.print("Masukkan jumlah stok baru: ");
        int stokBaru = input.nextInt();
        input.nextLine();

        alatUpdate.setNama(namaBaru);
        alatUpdate.setStok(stokBaru);

        // Pengecekan jika alat adalah AlatMasak atau AlatMakan
        if (alatUpdate instanceof AlatMasak) {
            System.out.print("Masukkan bahan baru: ");
            String bahanBaru = input.nextLine();
            ((AlatMasak) alatUpdate).setBahan(bahanBaru);
        } else if (alatUpdate instanceof AlatMakan) {
            System.out.print("Masukkan jenis baru: ");
            String jenisBaru = input.nextLine();
            ((AlatMakan) alatUpdate).setJenis(jenisBaru);
        }

        System.out.println("Data alat berhasil diperbarui!");
    }

    // Fungsi untuk menghapus alat
    public static void deleteAlat(Scanner input) {
        System.out.println("\n--- Hapus Alat Dapur ---");
        System.out.print("Masukkan ID alat yang ingin dihapus: ");
        int id = input.nextInt();
        input.nextLine();

        AlatDapur alatHapus = null;
        for (AlatDapur alat : stokList) {
            if (alat.getId() == id) {
                alatHapus = alat;
                break;
            }
        }

        if (alatHapus == null) {
            System.out.println("Alat dengan ID tersebut tidak ditemukan.");
            return;
        }

        stokList.remove(alatHapus);
        System.out.println("Alat berhasil dihapus!");
    }
}
