# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: http://doc.scrapy.org/en/latest/topics/item-pipeline.html
from scrapy.conf import settings

class appstore1Pipeline(object):
    def __init__(self):
        self.file = open('huaweistore.dat', 'wb')


    def process_item(self, item, spider):
        val = "{0}\t{1}\t{2}\t{3}\n".format(item['appid'],item['url'],item['title'],item['recommended'])
        self.file.write(item['appid'].encode())
        self.file.write('\t'.encode())
        self.file.write(item['title'])
        self.file.write('\t'.encode())
        self.file.write(item['intro'])
        self.file.write('\t'.encode())
        self.file.write(item['recommended'].encode())
        self.file.write(item['recommendedplus'])
        self.file.write('\n'.encode())


        return item
