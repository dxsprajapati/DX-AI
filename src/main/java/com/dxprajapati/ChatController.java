package com.dxprajapati;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin("*")
public class ChatController {

    @Autowired
    private GroqService groqService;

//    @Autowired
//    private ChatLogRepository chatLogRepository;

    @PostMapping
    public String chat(@RequestBody ChatRequest request) {
        //groqService.chat(request.getMessage());
        String response = "હાલમાં હું મારી AI સર્વિસને વધુ સારી અને સ્માર્ટ બનાવવા માટે કામ કરી રહ્યો છું 😊

એટલા માટે અત્યારે હું તમને AI આધારિત યોગ્ય જવાબ આપી શકતો નથી.
થોડો સમય આપશો 🙏

ખૂબ જલ્દી હું વધુ સારા અનુભવ સાથે ફરીથી તૈયાર થઈને તમને જાણ કરીશ 🚀

— દક્ષેશ પ્રજાપતિ AI સર્વિસ"
//        ChatLog log = new ChatLog(
//                request.getMessage(),
//                response,
//                LocalDateTime.now()
//        );

        //chatLogRepository.save(log);

        return response;
    }

//    @GetMapping("/logs")
//    public Object getLogs() {
//        return chatLogRepository.findAll();
//    }

    @GetMapping("/ping")
    public String ping() {
        return "DX-AI Running";
    }
}
