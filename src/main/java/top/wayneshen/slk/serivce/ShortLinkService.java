package top.wayneshen.slk.serivce;

/**
 * 短链接service
 *
 * @Author wayne
 * @Date 2020/6/2 11:03
 */
public interface ShortLinkService {

    /**
     * 生成短连接，对于code验重
     *
     * @param originLink originLink 原始链接
     * @return
     */
    String generateShortLink(String originLink, String validTime);

    /**
     * 查询一个短链接
     *
     * @param linkCode
     * @return originLink 原始链接
     */
    String findShortLink(String linkCode);


}
