package com.nq.qbr;

//import java.util.jar.Attributes;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSHandler extends DefaultHandler {

	RSSFeed rssFeed;// ���ڱ�����������е�channel
	RSSItem rssItem;// ���ڱ�����������е�item
	String lastElementName = ""; // ��Ǳ��������ڱ���ڽ������������ǹ��ĵļ�����ǩ�����������ǹ��ĵı�ǩ������ 0
	final int RSS_TITLE = 1;// ����title��ǩ,���� 1,ע��������title,�����Ƕ�������item��title��Ա������
	final int RSS_LINK = 2;// ����link��ǩ,���� 2
	final int RSS_DESCRIPTION = 3;// ����description��ǩ,���� 3
	final int RSS_CATEGORY = 4;// ����category��ǩ,���� 4
	final int RSS_PUBDATE = 5; // ����pubdate��ǩ,����
								// 5,ע��������pubdate,�����Ƕ�������item��pubdate��Ա������
	int currentstate = 0;

	public RSSHandler() {
	}

	public RSSFeed getFeed() {
		return rssFeed;// ͨ����������ѽ��������װ�� RSSFeed �����в�����
	}

	// ����ͨ������ DefaultHandler �� 5 ��������ʵ�� sax ����
	public void startDocument() throws SAXException {

		// ��������ڽ���xml�ĵ���һ��ʼִ��,һ��������Ҫ�ڸ÷����г�ʼ�������������п����õ��ı���
		rssFeed = new RSSFeed();
		rssItem = new RSSItem();
	}

	public void endDocument() throws SAXException {

		// �������������xml�ĵ���������ʱִ��,һ����Ҫ�ڸ÷����з��ػ򱣴������ĵ������������,������

		// �����Ѿ��ڽ��������аѽ��������rssFeed��,��������ʲôҲ����

	}

	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		// ��������ڽ�����ǩ��ʼ���ʱִ��,һ��������Ҫ�ڸ÷���ȡ�ñ�ǩ����ֵ,���������ǵ�rss�ĵ�

		// �в�û���κ����ǹ��ĵı�ǩ����,���������Ҫ��������е������ñ�Ǳ���currentstate,��

		// ������Ǵ����ĸ���ǩ

		if (localName.equals("channel")) {// channel�����ǩû���κ�ֵ�����ǹ��ĵ����ݣ�����currentstate��Ϊ0
			currentstate = 0;
			return;
		}
		if (localName.equals("item")) {
			// ����item��ǩ,�����¹���һ��RSSItem,�Ӷ�������(�Ѿ���������)item�����ӵ�,��

			// Ȼ�������Ѿ����浽rssFeed��itemlist��������

			rssItem = new RSSItem();
			return;
		}
		if (localName.equals("title")) {
			// ����title��ǩ,��currentstateΪ1,�����������ǹ��ĵ�����,������characters

			// �����л��Ԫ�����ݱ��浽rssItem������

			currentstate = RSS_TITLE;
			return;
		}
		if (localName.equals("description")) {
			// ����description��ǩ,��currentstateΪ3,�����������ǹ��ĵ�����,������characters

			// �����л��Ԫ�����ݱ��浽rssItem������

			currentstate = RSS_DESCRIPTION;
			return;
		}
		if (localName.equals("link")) {
			// ����link��ǩ,��currentstateΪ2,�����������ǹ��ĵ�����,������characters

			// �����л��Ԫ�����ݱ��浽rssItem������

			currentstate = RSS_LINK;
			return;
		}
		if (localName.equals("category")) {
			// ����category��ǩ,��currentstateΪ4,�����������ǹ��ĵ�����,������characters

			// �����л��Ԫ�����ݱ��浽rssItem������

			currentstate = RSS_CATEGORY;
			return;
		}
		if (localName.equals("pubDate")) {
			// ����pubDate��ǩ,��currentstateΪ5,�����������ǹ��ĵ�����,������characters

			// �����л��Ԫ�����ݱ��浽rssItem������

			currentstate = RSS_PUBDATE;
			return;
		}

		currentstate = 0;// ������������г����κα�ǩ,��currentstateΪ0,���ǲ�����
	}

	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {

		// �������һ��item�ڵ�������ͽ�rssItem��ӵ�rssFeed�С�
		if (localName.equals("item")) {
			rssFeed.addItem(rssItem);
			return;

		}
	}

	public void characters(char ch[], int start, int length) {// ��������ڽ�����ǩ����(����ʼ��ǣ��������֮��Ĳ���)ʱִ��,һ�������������ȡԪ��������
		String theString = new String(ch, start, length); // ��ȡԪ��������
		switch (currentstate) {// ����currentstate����ж����Ԫ�������������ǹ��ĵ��ĸ�Ԫ��
		case RSS_TITLE:
			rssItem.setTitle(theString);// ����titleԪ��,����rssItem��title����
			currentstate = 0;
			break;
		case RSS_LINK:
			rssItem.setLink(theString);// ����linkԪ��,����rssItem��link����
			currentstate = 0;
			break;
		case RSS_DESCRIPTION:
			rssItem.setDescription(theString);
			currentstate = 0;
			break;
		case RSS_CATEGORY:
			rssItem.setCategory(theString);
			currentstate = 0;
			break;
		case RSS_PUBDATE:
			rssItem.setPubDate(theString);
			currentstate = 0;
			break;
		default:
			return;
		}

	}

}
