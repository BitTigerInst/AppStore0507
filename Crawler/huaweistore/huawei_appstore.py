import scrapy
import re
import urllib.response
from scrapy.selector import Selector as se
from appstore.items import AppstoreItem as AI
class HuaweiSpider(scrapy.Spider):
    name="huawei"
    allowed_domain=["huawei.com"]

    start_urls=["http://appstore.huawei.com/more/all"]

    def parse(self,response):
        page=se(response)

        divs=page.xpath('//div[@class="game-info whole"]')

        for div in divs:
            item=AI.AppStoreItem()
            item['title']=div.xpath('.//h4[@class="title"]/a/text()').extract_first().encode('urf-8')
            item['url']=div.xpath('.//h4[@class="title"]/a/@href').extract_first()
            appid=re.match(r'http://.*/(.*)',item['url']).group(1)
            item['appid']=appid
            item['intro']=div.xpath('.//p[@class="content"]/text()').extract_first().encode('urf-8')
            yield item
