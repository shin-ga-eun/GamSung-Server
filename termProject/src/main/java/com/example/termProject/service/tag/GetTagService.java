package com.example.termProject.service.tag;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.termProject.domain.dto.tag.GetTagDto;
import com.example.termProject.domain.entity.Tag;
import com.example.termProject.repository.TagRepository;

@Service
public class GetTagService {
	
	final private TagRepository tagRepository;
	
	public GetTagService (TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	
	//인기있는 탭의 태그 리스트 -> 정렬순서: tagname별 cno의 갯수 내림차순
	public List<GetTagDto> getPopularTag (){
		List<Tag> getAllTag = tagRepository.findAll();
		List<GetTagDto> getPopularTag = new ArrayList<>();
		
		HashMap<String, Integer> tagToNum = new HashMap<>(); 
		List<Integer> valuelist = new ArrayList<>();
		List<String> keylist = new ArrayList<>();
		List<String[]> finalList = new ArrayList<>(); //최종리스트
		
		//tagToNum 생성 -> tagName, cno 수	
		for(int i=0; i<getAllTag.size(); i++) {
			String tagname = getAllTag.get(i).getTagname();
			
			if(tagToNum.containsKey(tagname)) {
				int num = tagToNum.get(tagname);
				num = num + 1;
				tagToNum.put(tagname, num);
			}
			else
				tagToNum.put(tagname, 1);
		}

		//2차원 배열에 저장
		Set set = tagToNum.entrySet();
		Iterator<String> keys = tagToNum.keySet().iterator();	
	
		while(keys.hasNext()) {
			String key = keys.next();
			
			keylist.add(key);
			valuelist.add(tagToNum.get(key));
		}
		
		
		for(int i=0; i<keylist.size(); i++) {
			String[] s = {keylist.get(i), valuelist.get(i).toString()};
			finalList.add(i,s);
		}
		
		//cno 내림차순 정렬
		String temp, temp2;
		for(int i=finalList.size()-2; i>=0; i--) {
			for(int j=0; j<=i; j++) {
				if( Integer.parseInt(finalList.get(j)[1]) < Integer.parseInt(finalList.get(j+1)[1])) {
					temp = finalList.get(j)[0];
					finalList.get(j)[0] = finalList.get(j+1)[0];
					finalList.get(j+1)[0] = temp;
					
					temp2 = finalList.get(j)[1];
					finalList.get(j)[1] = finalList.get(j+1)[1];
					finalList.get(j+1)[1] = temp2;
				}
			}
		}
		 
		//service 작성
		for(int i=0; i<finalList.size(); i++) {
			GetTagDto tag = new GetTagDto();
			tag.setTagname(finalList.get(i)[0]);
			
			getPopularTag.add(tag);
		}
		
		return getPopularTag;

	}
	
	
	//새로운 탭의 태그 리스트 -> 정렬순서: tagname별 regDate 최신순 -> tagno이 큰순
	public List<GetTagDto> getNewTag (){
		List<Tag> getAllTag = tagRepository.findAll();
		List<GetTagDto> getNewTag = new ArrayList<>();
		
		for(int i=getAllTag.size()-1; i>=0; i--) {
			GetTagDto tag = new GetTagDto();
			tag.setTagname(getAllTag.get(i).getTagname());
			
			getNewTag.add(tag);
		}
		
		return getNewTag;
	}
}
