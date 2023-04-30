package com.example.cumulusspringboot.controllers.oubaid;

import com.example.cumulusspringboot.entities.oubaid.BadWords;
import com.example.cumulusspringboot.entities.oubaid.Chat;
import com.example.cumulusspringboot.entities.oubaid.Message;
import com.example.cumulusspringboot.exception.oubaid.ChatAlreadyExistException;
import com.example.cumulusspringboot.exception.oubaid.ChatNotFoundException;
import com.example.cumulusspringboot.exception.oubaid.NoChatExistsInTheRepository;
import com.example.cumulusspringboot.repositories.oubaid.MessagesRepo;
import com.example.cumulusspringboot.services.oubaid.BadWordsService;
import com.example.cumulusspringboot.services.oubaid.ChatService;
import com.example.cumulusspringboot.services.oubaid.MessagesService;
import com.example.cumulusspringboot.services.oubaid.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/chats")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private BadWordsService badWordsService;

    @Autowired
    private MessagesService messagesService;

    @PostMapping("/addChat")
    public ResponseEntity<Chat> createChat(@RequestBody Chat chat) throws IOException {

        try {
            return new ResponseEntity<Chat>(chatService.addChat(chat), HttpStatus.CREATED);
        } catch (ChatAlreadyExistException e) {
            return new ResponseEntity("Chat Already Exist", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/GetAllChats")
    public ResponseEntity<List<Chat>> getAllChats() {
        try {
            return new ResponseEntity<List<Chat>>(chatService.findallchats(), HttpStatus.OK);
        } catch (NoChatExistsInTheRepository e) {
           return new ResponseEntity("List not found", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/GetChatById/{id}")
    public ResponseEntity<Chat> getChatById(@PathVariable long id) {
        try {
            return new ResponseEntity<Chat>(chatService.getById(id), HttpStatus.OK);
        } catch (ChatNotFoundException e) {
           return new ResponseEntity("Chat Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/GetChatByFirstUserName/{username}")
    public ResponseEntity<?> getChatByFirstUserName(@PathVariable String username) {
        try {
            HashSet<Chat> byChat = this.chatService.getChatByFirstUserName(username);
            return new ResponseEntity<>(byChat, HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity("Chat Not Exits", HttpStatus.CONFLICT);
        }
    }



    @GetMapping("/GetChatBySecondUserName/{username}")
    public ResponseEntity<?> getChatBySecondUserName(@PathVariable String username) {

        try {
            HashSet<Chat> byChat = this.chatService.getChatBySecondUserName(username);
            return new ResponseEntity<>(byChat, HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity("Chat Not Exits", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/GetChatByFirstUserNameOrSecondUserName/{username}")
    public ResponseEntity<?> getChatByFirstUserNameOrSecondUserName(@PathVariable String username) {

        try {
            HashSet<Chat> byChat = this.chatService.getChatByFirstUserNameOrSecondUserName(username);
            return new ResponseEntity<>(byChat, HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity("Chat Not Exits", HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/getChatByFirstUserNameAndSecondUserName")
    public ResponseEntity<?> getChatByFirstUserNameAndSecondUserName(@RequestParam("firstUserName") String firstUserName, @RequestParam("secondUserName") String secondUserName){

        try {
            HashSet<Chat> chatByBothEmail = this.chatService.getChatByFirstUserNameAndSecondUserName(firstUserName, secondUserName);
            return new ResponseEntity<>(chatByBothEmail, HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity("Chat Not Exits", HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/message/{chatId}")
    public ResponseEntity<Chat> addMessage(@RequestBody Message add , @PathVariable int chatId) throws ChatNotFoundException {
        return new ResponseEntity<Chat>(chatService.addMessage(add,chatId), HttpStatus.OK);
    }




    @GetMapping("/GetAllBadWords")
    public ResponseEntity<List<BadWords>> getAllBadWords() {
        try {
            return new ResponseEntity<List<BadWords>>(badWordsService.findAllBadWords(), HttpStatus.OK);
        } catch (NoChatExistsInTheRepository e) {
            return new ResponseEntity("List not found", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/checkMessage")
    public ResponseEntity<String> checkMessage(@RequestBody Message message) {
        try {
            if (message.getReplymessage() == null || badWordsService.containsBadWord(message)) {
                return ResponseEntity.badRequest().body("Message contains a bad word");
            } else {
                return ResponseEntity.ok("Message is clean");
            }
        } catch (NoChatExistsInTheRepository e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }




    @GetMapping("/GetAllMessages")
    public ResponseEntity<List<Message>> getAllMessages() {
        try {
            return new ResponseEntity<List<Message>>(messagesService.findallMessages(), HttpStatus.OK);
        } catch (NoChatExistsInTheRepository e) {
            return new ResponseEntity("List not found", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/keywords")
    public Map<String, Integer> getMostCommonKeywords() {
        return messagesService.getMostCommonKeywords();
    }


}
