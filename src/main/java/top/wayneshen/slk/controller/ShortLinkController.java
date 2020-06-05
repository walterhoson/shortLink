package top.wayneshen.slk.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.wayneshen.slk.serivce.ShortLinkService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 短链接controller
 *
 * @Author wayne
 * @Date 2020/6/2 11:02
 */
@RestController
@Slf4j
public class ShortLinkController {

    @Resource
    private ShortLinkService shortLinkService;

    /**
     * 生成短链接
     *
     * @param originLink 原始链接
     * @param validTime  有效时间，单位分钟
     * @return
     */
    @GetMapping("/generate")
    @ResponseBody
    public String generateShortLink(@RequestParam String originLink,
                                    @RequestParam String validTime, HttpServletRequest request) {
        if (StringUtils.isBlank(originLink)) {
            return "原始链接不可为空";
        }
        if (!StringUtils.isNumeric(validTime)) {
            return "validTime必须为整数";
        }
        String linkCode = shortLinkService.generateShortLink(originLink, validTime);
        String requestUrl = request.getRequestURL().toString();
        String newLink = requestUrl.substring(0, requestUrl.indexOf("/generate")) + "/s/" + linkCode;
        return "生成成功，短链接url：" + newLink;
    }


}
