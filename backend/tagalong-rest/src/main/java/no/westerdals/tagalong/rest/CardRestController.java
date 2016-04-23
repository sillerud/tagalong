package no.westerdals.tagalong.rest;

import no.westerdals.tagalong.MessageConstant;
import no.westerdals.tagalong.model.Card;
import no.westerdals.tagalong.model.UserCredentials;
import no.westerdals.tagalong.mongodb.CardRepository;
import no.westerdals.tagalong.responses.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static no.westerdals.tagalong.responses.ResultResponse.*;

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

    @RequestMapping(value="/{cardId}", method=RequestMethod.DELETE)
    public ResultResponse deleteCard(@PathVariable String cardId, Principal principal) {
        UserCredentials user = ((UserCredentials) ((Authentication)principal).getPrincipal());
        Card card = cardRepository.findOne(cardId);
        if (card == null)
            return newErrorResult(MessageConstant.CARD_NOT_FOUND);
        if (!card.getUserId().equals(user.getUserId()))
            return newErrorResult(MessageConstant.ACCESS_DENIED);
        cardRepository.delete(cardId);
        return newOkResult(MessageConstant.CARD_DELETED);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResultResponse createCard(@RequestBody  Card card) {
        card.setId(null);
        Card result = cardRepository.insert(card);
        return newOkResult(MessageConstant.CARD_CREATED, result);
    }
}