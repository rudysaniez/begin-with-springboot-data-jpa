package com.me.work.api.jpa.mapper;

import org.mapstruct.Mapper;

import com.me.work.character.api.model.PageMetadata;

@Mapper
public interface PageMetadataMapper {

	/**
	 * @param pageMetadata
	 * @return {@link PageMetadata}
	 */
	public PageMetadata toModel(org.springframework.hateoas.PagedModel.PageMetadata pageMetadata);
}
