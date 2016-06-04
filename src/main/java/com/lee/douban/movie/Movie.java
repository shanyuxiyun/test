package com.lee.douban.movie;

/*
 <tr class="item"> 
 	<td width="100" valign="top"> <a class="nbg" href="https://movie.douban.com/subject/24397586/" title="С��Ф��"> <img src="https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2249261202.jpg" alt="С��Ф��" class=""> </a> </td> 
 	<td valign="top"> 
	    <div class="pl2"> 
		       <a href="https://movie.douban.com/subject/24397586/" class=""> С��Ф�� / <span style="font-size:12px;">С��Ф�����Ӱ / �����޵���������Ӱ֮���(��)</span> </a> 
		       <span style="font-size: 13px; padding-left: 3px; color: #00A65F;">[�ɲ���]</span> 
		   	   <p class="pl">2015-02-06(Ӣ��) / 2015-07-17(�й���½) / ��˹͡�������� / Լ����˹����˹ / ŷ�׵¡��������� / ���¡�Τ�� / ���ء����� / ��ķ������ / ���ϡ����� / ���ɡ�����ŵ / ���ꡤ̩�� / �ܿˡ�����ɭ / ��������ŵ�� / ���������� / �����ء�ά��˹...</p> 
			   <div class="star clearfix"> 
				    <span class="allstar45"></span> 
				    <span class="rating_nums">8.4</span> 
				    <span class="pl">(46062������)</span> 
			   </div> 
	  	</div>
	</td> 
</tr>
 */
public class Movie {

	private String name;
	private String tag;
	private String rating;
	private String logoUrl;
	private String nameAliases;
	private int playStatus;
	private String startDate;
	private String actors;
	private int commentPeople;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Movie(String name, String tag, String rating, String logoUrl, String nameAliases, int playStatus,
			String startDate, String actors, int commentPeople, String url) {
		super();
		this.name = name;
		this.tag = tag;
		this.rating = rating;
		this.logoUrl = logoUrl;
		this.nameAliases = nameAliases;
		this.playStatus = playStatus;
		this.startDate = startDate;
		this.actors = actors;
		this.commentPeople = commentPeople;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getNameAliases() {
		return nameAliases;
	}

	public void setNameAliases(String nameAliases) {
		this.nameAliases = nameAliases;
	}

	public int getPlayStatus() {
		return playStatus;
	}

	public void setPlayStatus(int playStatus) {
		this.playStatus = playStatus;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public int getCommentPeople() {
		return commentPeople;
	}

	public void setCommentPeople(int commentPeople) {
		this.commentPeople = commentPeople;
	}

	public Movie() {
	}

	@Override
	public String toString() {
		return "Movie [name=" + name + ", tag=" + tag + ", rating=" + rating + ", logoUrl=" + logoUrl + ", nameAliases="
				+ nameAliases + ", playStatus=" + playStatus + ", startDate=" + startDate + ", actors=" + actors
				+ ", commentPeople=" + commentPeople + ", url=" + url + "]";
	}


}
