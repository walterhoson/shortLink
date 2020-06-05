package top.wayneshen.slk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.wayneshen.slk.serivce.ShortLinkService;

import javax.annotation.Resource;

/**
 * 短链接重定向controller
 *
 * @Author wayne
 * @Date 2020/6/2 12:30
 */
@Controller
@RequestMapping("/s")
public class RedirectController {

    @Resource
    private ShortLinkService shortLinkService;

    public static final String NEXT_HOP_PREFIX = "redirect:";

    /**
     * 短链接重定向
     *
     * @param code 短链接code
     * @return
     */
    @GetMapping("/{code}")
    public String redirectShortLink(@PathVariable String code) {
        String originLink = shortLinkService.findShortLink(code);
        if (originLink == null) {
            return "";
        }
        return NEXT_HOP_PREFIX + originLink;
    }

}
