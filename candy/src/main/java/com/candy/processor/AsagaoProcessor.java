package com.candy.processor;

import com.candy.model.*;
import com.candy.repository.CandyBoxRepository;
import com.candy.repository.ColorRepository;
import com.candy.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

    @Service
    public class AsagaoProcessor implements ProcessorInterface{
    @Autowired
    ColorRepository colorRepository;

    @Autowired
    CandyBoxRepository candyBoxRepository;
        @Autowired
        private CustomerOrderRepository customerOrderRepository;

     /*
Les bonbons ASAGAO ne peuvent être commandés que le matin entre 6h et midi.
La couleur de chaque bonbon est définie de façon aléatoire.
La livraison se fait par CandyBox contenant chacune 50 bonbons maximum.
     */
    /*
     Mon client à passer une commande avec quantité totale de bonbons (totalQuantity)
     je dois mettre des bonbons de couleurs aléatoire dans des boites contenant au maximum 50 bonbons
     il va donc falloir boucler sur la quantité totale commandé, chaque bonbons commandé ira dans une boite,
     dès que cette boite à 50 bonbons, on prend une nouvelle boîte
     */

    @Override
    public CandyBox createOrder(int totalQuantity, CandyTagEnum candyTagName){


        List<ItemCandyBox> totalCandyItem = getCandyBoxes(totalQuantity, candyTagName);
        CandyBox candyBox = new CandyBox();
        candyBox.setTotalCandyBox(totalCandyItem);

        CustomerOrder order = new CustomerOrder();
        order.setId(1);
        customerOrderRepository.save(order);
        candyBox.setCustomerOrder(order);
        candyBoxRepository.save(candyBox);
        return candyBox;
    }

   public List<ItemCandyBox> getCandyBoxes(int totalQuantity, CandyTagEnum candyTagName) {
       boolean isCheckTime = checkTime();
       List<Color> colors = (List<Color>) colorRepository.findAll();

       CandyTagEnum isAsagao = CandyTagEnum.ASAGAO;

       List<ItemCandyBox> totalCandybox = new ArrayList<>();

       if (isCheckTime && candyTagName == isAsagao) {
           // Pour chaque bonbon commandé de ma qté totale de bonbons commandés,
           // alors je mets ce bonbon d'une couleur aléatoire dans la box.
           // Attention la box à une qté max = 50
               for (int i = 1; i <= totalQuantity; i++) {
                   // je récupère une couleur aléatoire de ma liste Color
                   Color randomColor = this.getRandomColor(colors);
                   for(int j = 1; j <=50; j++){
                       ItemCandyBox item = new ItemCandyBox();
                       item.setColor(randomColor);
                   totalCandybox.add(item);
                   }
               }
       } else {
           throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "La commande n'est pas une Asagao");
       }
       // throw exception if not equals to isChecktime and Is asagao
      return totalCandybox;
   }

    private Color getRandomColor(List<Color> colors) {
            Random random = new Random();
            int randomIndex = random.nextInt(colors.size());
            return colors.get(randomIndex);
    }

    private boolean checkTime() {
        //Convert Instance to LocalTIme
        LocalDateTime currentTime = LocalDateTime.now();

        if (currentTime.getHour() > 6  && currentTime.getHour() < 12) {
            System.out.println("C'est ok le client peut passer la commande");
            return true;
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "La commande est passée en dehors des horaires : 6h-12h");
        }
    }
}