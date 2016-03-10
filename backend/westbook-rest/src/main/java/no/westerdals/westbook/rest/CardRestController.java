package no.westerdals.westbook.rest;

import no.westerdals.westbook.MessageConstant;
import no.westerdals.westbook.model.Card;
import no.westerdals.westbook.model.User;
import no.westerdals.westbook.model.UserCredentials;
import no.westerdals.westbook.mongodb.CardRepository;
import no.westerdals.westbook.responses.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static no.westerdals.westbook.responses.ResultResponse.*;

@RestController
@RequestMapping("/rest/v1/cards")
public class CardRestController {
    @Autowired
    private CardRepository cardRepository;

    @RequestMapping(method=RequestMethod.GET)
    public List<Card> getOwnCards(Principal principal) {
        UserCredentials user = ((UserCredentials) ((Authentication)principal).getPrincipal());
        return cardRepository.findByUserId(user.getUserId());
    }

    @RequestMapping(value="/{cardId}", method=RequestMethod.GET)
    public Card getCard(@PathVariable String cardId) {
        return cardRepository.findOne(cardId);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResultResponse createCard(@RequestBody  Card card) {
        card.setId(null);
        Card result = cardRepository.insert(card);
        return newOkResult(MessageConstant.CARD_CREATED, result);
    }
}