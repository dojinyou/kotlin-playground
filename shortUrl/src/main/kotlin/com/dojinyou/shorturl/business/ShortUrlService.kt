package com.dojinyou.shorturl.business

import java.net.URL

interface ShortUrlService {
    /**
     * long url을 받아서 short url로 변환합니다.
     * 고민사항
     * 1. longUrl이 이미 있다면 어떻게 해야할까?
     * - 동일한 longUrl에 대한 다른 record가 생기지 않도록 기존 값을 조회하여 돌려준다.
     *   - 동일한 longUrl이 있더라도 새로운 record를 만든다.
     * 2. shortValue를 저장하는 게 나을까? shortUrl을 저장하는 게 나을까?
     *   - 어차피 공개된 뒤에는 shortUrl의 shortValue를 제외한 path가 변경되면 안된다?
     *   - 추후에 변경되었을 때에 path만 redirect하고 shortValue로 비교한다?
     */
    fun create(longUrl: URL): URL
}
