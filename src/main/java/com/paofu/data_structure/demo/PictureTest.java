package com.paofu.data_structure.demo;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Description: 测试图片压缩
 * @Author: 泡芙和树
 * @Date: 2022/2/14 10:23
 */
@RestController
@RequestMapping("/images")
public class PictureTest {

    /**
     * 压缩图片
     * @param imgSrc  源图片地址
     * @param imgDist  目标图片地址
     * @param widthDist  压缩后图片的宽度
     * @param heightDist  压缩后图片的高度
     * @param rate  压缩的比例
     */
    public static void reduceImg(String imgSrc, String imgDist, int widthDist, int heightDist, Float rate) {
        try {
            File srcFile = new File(imgSrc);
            // 检查图片文件是否存在
            if (!srcFile.exists()) {
                System.out.println("文件不存在");
            }
            // 如果比例不为空则说明是按比例压缩
            if (rate != null && rate > 0) {
                //获得源图片的宽高存入数组中
                int[] results = getImgWidthHeight(srcFile);
                if (results == null || results[0] == 0 || results[1] == 0) {
                    return;
                } else {
                    //按比例缩放或扩大图片大小，将浮点型转为整型
                    widthDist = (int) (results[0] * rate);
                    heightDist = (int) (results[1] * rate);
                }
            }
            // 开始读取文件并进行压缩
            Image src = ImageIO.read(srcFile);

            // 构造一个类型为预定义图像类型之一的 BufferedImage
            BufferedImage tag = new BufferedImage((int) widthDist, (int) heightDist, BufferedImage.TYPE_INT_RGB);

            //绘制图像  getScaledInstance表示创建此图像的缩放版本，返回一个新的缩放版本Image,按指定的width,height呈现图像
            //Image.SCALE_SMOOTH,选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
            tag.getGraphics().drawImage(src.getScaledInstance(widthDist, heightDist, Image.SCALE_SMOOTH), 0, 0, null);

            //创建文件输出流
            FileOutputStream out = new FileOutputStream(imgDist);
            //将图片按JPEG压缩，保存到out中
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
            //关闭文件输出流
            out.close();
        } catch (Exception ef) {
            ef.printStackTrace();
        }
    }

    /**
     * 获取图片宽度和高度
     *
     * @return 返回图片的宽度
     */
    public static int[] getImgWidthHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int[] result = { 0, 0 };
        try {
            // 获得文件输入流
            is = new FileInputStream(file);
            // 从流里将图片写入缓冲图片区
            src = ImageIO.read(is);
            // 得到源图片宽
            result[0] =src.getWidth(null);
            // 得到源图片高
            result[1] =src.getHeight(null);
            is.close();  //关闭输入流
        } catch (Exception ef) {
            ef.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) throws IOException {

//        File srcFile = new File("D:\\test1.jpg");
//        File distFile = new File("D:\\test3.jpg");
//        long startTime = System.currentTimeMillis();
//        System.out.println("压缩前图片大小：" + srcFile.length());
//        reduceImg("D:\\test1.jpg", "D:\\test3.jpg", 500, 500, 0.5f);
//        System.out.println("压缩后图片大小：" + distFile.length());
//
//        Thumbnails.of(new File("D:\\test1.jpg")).size(640, 480).outputFormat("jpg");
////                .toFile("D:\\test4.jpg");
//        long endTime = System.currentTimeMillis();
//        System.out.println("耗时：" + (endTime - startTime) + "秒");
    }

    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file) {
        imageMethod(file);
    }

    public static void imageMethod(MultipartFile file){

        // region/-------创建缩略图存放的文件夹-------/
        // 获得项目根目录
        String path = System.getProperty("user.dir");
        // 定义新文件夹路径
        String dirPath = path + File.separator + "image_cache";
        // 根据路径生成文件夹
        File dir = new File(dirPath);
        dir.mkdirs();

        // 定义缩略图的全路径
        String fileSuffix = file.getOriginalFilename();
//        String contextPath = dirPath + File.separator + fileSuffix;
        String contextPath = "D:\\image_cache" + File.separator + fileSuffix;
        // 压缩图片
        MultipartFile newFile = null;
        try {
            // 自定义宽高压缩（压缩方法很多，可以根据需求更改）
//            Thumbnails.of(file.getInputStream()).forceSize(360, 360).toFile(contextPath);
//            Thumbnails.of(file.getInputStream()).sourceRegion(Positions.CENTER, 800, 800).size(360, 360).keepAspectRatio(false)
//                    .toFile("D:\\image_cache\\test2.jpg");
            System.out.println("原文件的大小为：" + file.getSize());
            Thumbnails.of(file.getInputStream()).crop(Positions.CENTER).size(360, 360).toFile(contextPath);
            // 压缩完成后将缩略图生成MultipartFile
            FileItem fileItem = createFileItem(contextPath);
            newFile = new CommonsMultipartFile(fileItem);

        } catch (IOException e) {
            e.printStackTrace();
        }
        // 上面的“newFile”就是缩略图转换后的MultipartFile，就可以拿来用了

        // 程序处理完后把生成的缩略图删除
        File image = new File(contextPath);

//        if (image.exists()) {
//            image.delete();
//        }

    }



    /**
     * 获取压缩后的图片,File 转为 MultipartFile
     *
     */
    public static FileItem createFileItem(String contextPath) {
        {
            FileItemFactory factory = new DiskFileItemFactory(16, null);
            String textFieldName = "textField";
            int num = contextPath.lastIndexOf(".");
            String extFile = contextPath.substring(num);
            FileItem item = factory.createItem(textFieldName, "text/plain", true,
                    "MyFileName" + extFile);
            File newFile = new File(contextPath);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            try {
                FileInputStream fis = new FileInputStream(newFile);
                OutputStream os = item.getOutputStream();
                while ((bytesRead = fis.read(buffer, 0, 8192))
                        != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return item;
        }
    }
}
