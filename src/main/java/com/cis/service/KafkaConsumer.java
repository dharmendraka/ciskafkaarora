package com.cis.service;

import com.cis.model.Book;
import com.cis.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private StorageService service;

    public void setName(String line) {
        Book book = new Book();
        book.setName(line);
        bookRepository.save(book);
    }

    @KafkaListener(topics = "CISTopic")
    public void getMessage(String message) {
        System.out.println(message);
        Book book = new Book();
        book.setName(message);
        bookRepository.save(book);
        //        InputStream inputStream = null;
//        try {
//            inputStream = multipartFile.getInputStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
//                .lines()
//                .forEach(line ->setName(line));
//
//        service.uploadFile(multipartFile);

    }
}
