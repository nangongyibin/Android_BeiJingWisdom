package com.ngyb.beijingwisdom.bean;

import java.util.List;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/7 17:35
 */
public class NewsListBean {

    /**
     * data : {"countcommenturl":"http://zhbj.qianlong.com/client/content/countComment/","more":"/10002/list_1.json","news":[{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35311,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35312,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35313,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35314,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35315,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35316,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35310,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35318,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35314","id":35314,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:54","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35117","id":35117,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 08:45","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"}],"title":"专题","topic":[{"description":"11111111","id":10101,"listimage":"/10002/1452327318UU91.jpg","sort":1,"title":"专题","url":"/10002/list_1.json"},{"description":"22222222","id":10100,"listimage":"/10002/1452327318UU91.jpg","sort":2,"title":"222222222222","url":"/10002/list_1.json"}],"topnews":[{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35301","id":35301,"pubdate":"2014-04-08 14:24","title":"专题1","topimage":"/10002/1452327318UU91.jpg","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35125","id":35125,"pubdate":"2014-04-08 09:09","title":"专题2","topimage":"/10002/1452327318UU92.jpg","type":"news","url":"/10002/724D6A55496A11726628.html"}]}
     * retcode : 200
     */

    private DataBean data;
    private int retcode;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public static class DataBean {
        /**
         * countcommenturl : http://zhbj.qianlong.com/client/content/countComment/
         * more : /10002/list_1.json
         * news : [{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35311,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35312,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35313,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35314,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35315,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35316,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35310,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35318,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35314","id":35314,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:54","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35117","id":35117,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2014-04-08 08:45","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"}]
         * title : 专题
         * topic : [{"description":"11111111","id":10101,"listimage":"/10002/1452327318UU91.jpg","sort":1,"title":"专题","url":"/10002/list_1.json"},{"description":"22222222","id":10100,"listimage":"/10002/1452327318UU91.jpg","sort":2,"title":"222222222222","url":"/10002/list_1.json"}]
         * topnews : [{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35301","id":35301,"pubdate":"2014-04-08 14:24","title":"专题1","topimage":"/10002/1452327318UU91.jpg","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35125","id":35125,"pubdate":"2014-04-08 09:09","title":"专题2","topimage":"/10002/1452327318UU92.jpg","type":"news","url":"/10002/724D6A55496A11726628.html"}]
         */

        private String countcommenturl;
        private String more;
        private String title;
        private List<NewsBean> news;
        private List<TopicBean> topic;
        private List<TopnewsBean> topnews;

        public String getCountcommenturl() {
            return countcommenturl;
        }

        public void setCountcommenturl(String countcommenturl) {
            this.countcommenturl = countcommenturl;
        }

        public String getMore() {
            return more;
        }

        public void setMore(String more) {
            this.more = more;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<NewsBean> getNews() {
            return news;
        }

        public void setNews(List<NewsBean> news) {
            this.news = news;
        }

        public List<TopicBean> getTopic() {
            return topic;
        }

        public void setTopic(List<TopicBean> topic) {
            this.topic = topic;
        }

        public List<TopnewsBean> getTopnews() {
            return topnews;
        }

        public void setTopnews(List<TopnewsBean> topnews) {
            this.topnews = topnews;
        }

        public static class NewsBean {
            /**
             * comment : true
             * commentlist : /10002/comment_1.json
             * commenturl : http://zhbj.qianlong.com/client/user/newComment/35319
             * id : 35311
             * listimage : /10002/1452327318UU91.jpg
             * pubdate : 2014-04-08 14:58
             * title : 专题
             * type : news
             * url : /10002/724D6A55496A11726628.html
             */

            private boolean comment;
            private String commentlist;
            private String commenturl;
            private int id;
            private String listimage;
            private String pubdate;
            private String title;
            private String type;
            private String url;

            public boolean isComment() {
                return comment;
            }

            public void setComment(boolean comment) {
                this.comment = comment;
            }

            public String getCommentlist() {
                return commentlist;
            }

            public void setCommentlist(String commentlist) {
                this.commentlist = commentlist;
            }

            public String getCommenturl() {
                return commenturl;
            }

            public void setCommenturl(String commenturl) {
                this.commenturl = commenturl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getListimage() {
                return listimage;
            }

            public void setListimage(String listimage) {
                this.listimage = listimage;
            }

            public String getPubdate() {
                return pubdate;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class TopicBean {
            /**
             * description : 11111111
             * id : 10101
             * listimage : /10002/1452327318UU91.jpg
             * sort : 1
             * title : 专题
             * url : /10002/list_1.json
             */

            private String description;
            private int id;
            private String listimage;
            private int sort;
            private String title;
            private String url;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getListimage() {
                return listimage;
            }

            public void setListimage(String listimage) {
                this.listimage = listimage;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class TopnewsBean {
            /**
             * comment : true
             * commentlist : /10002/comment_1.json
             * commenturl : http://zhbj.qianlong.com/client/user/newComment/35301
             * id : 35301
             * pubdate : 2014-04-08 14:24
             * title : 专题1
             * topimage : /10002/1452327318UU91.jpg
             * type : news
             * url : /10002/724D6A55496A11726628.html
             */

            private boolean comment;
            private String commentlist;
            private String commenturl;
            private int id;
            private String pubdate;
            private String title;
            private String topimage;
            private String type;
            private String url;

            public boolean isComment() {
                return comment;
            }

            public void setComment(boolean comment) {
                this.comment = comment;
            }

            public String getCommentlist() {
                return commentlist;
            }

            public void setCommentlist(String commentlist) {
                this.commentlist = commentlist;
            }

            public String getCommenturl() {
                return commenturl;
            }

            public void setCommenturl(String commenturl) {
                this.commenturl = commenturl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPubdate() {
                return pubdate;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTopimage() {
                return topimage;
            }

            public void setTopimage(String topimage) {
                this.topimage = topimage;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
