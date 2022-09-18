package com.kjh.jspaframeboot.controller;

import com.kjh.jspaframeboot.request.PostCreateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class PostController {


    //Sample Get Route
    @GetMapping("/posts")
    public String get() {
        return "Hello world";
    }

    // Http Method
    // GET , POST , PATCH , DELETE , OPTIONS, HEAD ,PUT , TRACE, CONNECT
    // 특징을 알아야한다
    // 글 등록
    // POST Method

    @PostMapping("/posts_form_urlencoded")
    public String post_urlencoded(@RequestParam String title, @RequestParam String content) {
        log.info("title={}, content={}", title, content);
        return "Hello world";
    }


    /**
     * 위와 동일하지만 매개변수를 Map 으로 받는 경우
     */
    @PostMapping("/posts_pramMap")
    public String posts_pramMap(@RequestParam Map<String,String> params) {
        log.info("params={}",params);
        return "Hello world";
    }

    /**
     * 위와 동일하지만 해당 값에대한 클래스를 만들어 받는 경우
     */
    @PostMapping("/posts_recv_object")
    public String posts_recv_object(PostCreateDto postCreateDto) {
        log.info("params={}",postCreateDto.toString());
        return "Hello world";
    }
// 데이터를 검증하는 이유

    // 1. client 개발자가 깜빡할 수 있다. 실수로 값을 안보낼수 있다.
    // 2. client bug로 값이 누락 될 수 있다.
    // 3. 외부에 나쁜사람이 값을 임의로 조작해서 보낼 수 있다.
    // 4. DB에 값을 저장할 때 의도치 않은 오류 가 발생 할 수 있다.
    // 5. 서버 개발자의 편안 함을 위해서
    @PostMapping("/posts_json")
    public Map<String,String> posts_json(@RequestBody @Valid PostCreateDto params, BindingResult result) throws Exception {
        if(result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String,String> errorMap = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                String fieldName = fieldError.getField(); //에러발생 필드의 들어온값
                String errorMessage = fieldError.getDefaultMessage();//...에러메시지
                errorMap.put(fieldName,errorMessage); //에러를 Map에 넣어 둔다.
            }
            return errorMap;
        }

        return new HashMap<>();
    }

//        String title = params.getTitle();
//        if(title == null || title.equals("")){
//            // 이런 종류의 검증이 안좋은 이유
//            // 1. 빡세다 (노가다)
//            // 2. 개발팁 -> 무언가 3번 이상 반복작업을 할 때 내가 뭔가 잘못하고 있는 건 아닌지 의심한다.
//            // 3. 누락가능성
//            // 4. 생각보다 검증해야할 게 많다. (꼼꼼하지 않을 수 도 있다)
//            // 이런 경우를 고려 해야 한다.
//            // {"title" : "           "}
//            // {"title" : "..수십억 글자"}
//            throw new Exception("타이틀 값이 없다.");
//        }

    @PostMapping("/posts_use_controller_advice")
    public Map<String,String> posts_use_(@RequestBody @Valid PostCreateDto params) throws Exception {

        return new HashMap<>();
    }
}
