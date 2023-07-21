package com.candy.processor;

import com.candy.model.*;
import com.candy.repository.CandyBoxRepository;
import com.candy.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;


import java.time.Instant;
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

    @Override
    public void build() {
        System.out.println("Build AsagaoProcessor");
    }

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

    public CandyBox createAsagao(Date orderDateTime, int totalQuantity, CandyTag candyTagName){
        //Convert Date to Instance
        Instant instant = orderDateTime.toInstant();
        //Convert Instance to LocalTIme
        LocalTime currentTime = LocalTime.from(instant.atZone(ZoneId.systemDefault()));

        CandyTagEnum candyTagEnum = candyTagName.getCandyTagName();
        List<ItemCandyBox> totalCandyItem = getCandyBoxes(currentTime, totalQuantity, candyTagEnum);
        CandyBox candyBox = new CandyBox();
        candyBox.setTotalCandyBox(totalCandyItem);
        candyBoxRepository.save(candyBox);
        return candyBox;
    }

   public List<ItemCandyBox> getCandyBoxes(LocalTime currentTime, int totalQuantity, CandyTagEnum candyTagName) {
       boolean isCheckTime = checkTime(currentTime);
       List<Color> colors = (List<Color>) colorRepository.findAll();

       CandyTagEnum isAsagao = CandyTagEnum.ASAGAO;
       ItemCandyBox itemAsagaoCandyBox = new ItemCandyBox();

       List<ItemCandyBox> totalCandybox = new ArrayList<>();

       if (isCheckTime && candyTagName == isAsagao) {
           // Pour chaque bonbon commandé de ma qté totale de bonbons commandés,
           // alors je mets ce bonbon d'une couleur aléatoire dans la box.
           // Attention la box à une qté max = 50
               for (int i = 1; i <= totalQuantity; i++) {
                   // je récupère une couleur aléatoire de ma liste Color
                   Color randomColor = this.getRandomColor(colors);
                   for(int j = 1; j <=50; j++){
                   itemAsagaoCandyBox.setQuantity((parseInt(String.valueOf(randomColor))));
                   totalCandybox.add(itemAsagaoCandyBox);
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

    private boolean checkTime(LocalTime currentTime) {

        LocalTime startTime = LocalTime.of(6, 0); // 6am
        LocalTime endTime = LocalTime.of(12, 0); // 12pm (noon)

        if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
            System.out.println("C'est ok le client peut passer la commande");
            return true;
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "La commande est passée en dehors des horaires : 6h-12h");
        }
    }
}