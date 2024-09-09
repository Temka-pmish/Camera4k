import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

public class WorkWithCamera4k {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("OpenCV Version: " + Core.VERSION);
    }

    public static void main(String[] args) {

        VideoCapture videoCapture = new VideoCapture(0); // Камера по умолчанию


        videoCapture.set(Videoio.CAP_PROP_FRAME_WIDTH, 3840);
        videoCapture.set(Videoio.CAP_PROP_FRAME_HEIGHT, 2160);

        if (!videoCapture.isOpened()) {
            System.out.println("Ошибка: не удалось открыть камеру.");
            return;
        }


        double frameWidth = videoCapture.get(Videoio.CAP_PROP_FRAME_WIDTH);
        double frameHeight = videoCapture.get(Videoio.CAP_PROP_FRAME_HEIGHT);
        System.out.println("Разрешение видео: " + frameWidth + "x" + frameHeight);


        VideoWriter videoWriter = new VideoWriter("output_4k.mp4",
                VideoWriter.fourcc('H', '2', '6', '4'), 30,
                new Size(frameWidth, frameHeight), true);

        if (!videoWriter.isOpened()) {
            System.out.println("Ошибка: не удалось открыть файл для записи.");
            return;
        }


        Mat frame = new Mat();
        System.out.println("Начало записи видео в 4K...");


        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 10000) { // 10 сек
            if (videoCapture.read(frame)) {
                videoWriter.write(frame);
            } else {
                System.out.println("Ошибка: не удалось захватить кадр.");
                break;
            }
        }


        videoCapture.release();
        videoWriter.release();
        System.out.println("Видео сохранено как output_4k.mp4");
    }
}
